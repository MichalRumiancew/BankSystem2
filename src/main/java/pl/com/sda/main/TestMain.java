package pl.com.sda.main;

import pl.com.sda.dao.ClientDAO;
import pl.com.sda.dao.ClientFileDAO;
import pl.com.sda.model.AbstractAccount;
import pl.com.sda.model.Client;
import pl.com.sda.model.CurrentAccount;
import pl.com.sda.model.SavingAccount;

import java.util.Arrays;
import java.util.List;

public class TestMain {

    public static void main(String[] args) {

        Client c1 = new Client("Jan", "Kowalski",
                "jkowalski", "1234", "Warszawa", null);

        Client c2 = new Client("Michał", "Nowak",
                "mnowak", "1234", "Kraków", null);


        AbstractAccount c1a1 = new CurrentAccount();
        AbstractAccount c1a2 = new SavingAccount();

        AbstractAccount c2a1 = new CurrentAccount();
        AbstractAccount c2a2 = new SavingAccount();

        c1a1.setBalance(100);
        c2a2.setBalance(1005);


        c1.setAccounts(Arrays.asList(c1a1, c1a2));
        c2.setAccounts(Arrays.asList(c2a1, c2a2));

        List<Client> clients = Arrays.asList(c1, c2);

        ClientDAO dao = new ClientFileDAO();
        dao.save(clients);

        //**********************************

//        List<Client> clientsFromFile = dao.get();
//        clientsFromFile.forEach(System.out::println);

        ((ClientFileDAO) dao).close();

    }
}
