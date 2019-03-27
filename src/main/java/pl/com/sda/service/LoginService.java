package pl.com.sda.service;

import pl.com.sda.dao.ClientDAO;
import pl.com.sda.dao.ClientFileDAO;
import pl.com.sda.model.Client;

import java.util.List;

public class LoginService {

    private List<Client> clients;

    private ClientDAO clientDAO;

    public LoginService(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
        this.clients = clientDAO.get();
    }

    public Client login(String login, String password) {

        System.out.println(clients.size());

        return clients.stream()
                .filter(c -> c.getLogin().equals(login)
                        && c.getPassword().equals(password))
                .findFirst()
                .get();
    }


}
