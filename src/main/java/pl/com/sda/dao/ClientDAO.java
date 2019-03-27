package pl.com.sda.dao;

import pl.com.sda.model.Client;

import java.util.List;

public interface ClientDAO {

    void save(List<Client> clients);

    List<Client> get();

}
