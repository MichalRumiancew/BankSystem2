package pl.com.sda.main;

import pl.com.sda.dao.ClientDAO;
import pl.com.sda.dao.ClientFileDAO;
import pl.com.sda.model.Client;
import pl.com.sda.service.BankService;
import pl.com.sda.service.LoginService;

import java.util.Scanner;

public class MainMenu {

    public static void main(String[] args) {

        ClientDAO clientDAO = new ClientFileDAO();
        LoginService loginService = new LoginService(clientDAO);

        BankService bankService = new BankService(clientDAO);

        Scanner sc = new Scanner(System.in);
        int choice;



        do {

            System.out.println("0. Wyjście z programu");
            System.out.println("1. Logowanie");

            choice = sc.nextInt();

            switch (choice) {
                case 0: System.exit(1); break;

                case 1: {

                    System.out.println("Podaj login:");
                    String login = sc.next();

                    System.out.println("Podaj hasło:");
                    String pass = sc.next();

                    Client client = loginService.login(login, pass);

                    if (client == null) {
                        System.out.println("Nieudane logowanie");
                        System.exit(1);
                    }

                    break;

                }

                default:
                    System.out.println("Nieznana operacja"); break;

            }

        } while (choice != 0);

        ((ClientFileDAO) clientDAO).close();

    }

}
