package pl.com.sda.service;

import pl.com.sda.dao.ClientDAO;
import pl.com.sda.model.AbstractAccount;
import pl.com.sda.model.Client;

import java.util.List;

public class BankService {

    private List<Client> clients;

    private ClientDAO clientDAO;

    public BankService(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
        this.clients = clientDAO.get();
    }

    public AbstractAccount getAccountByNumber(String number) {

        return clients.stream()
                .map(c -> c.getAccounts())
                .flatMap(list -> list.stream())
                .filter(a -> a.getNumber().equals(number))
                .findFirst()
                .orElseGet(null);
    }

    public void save() {
        clientDAO.save(clients);
    }


}
