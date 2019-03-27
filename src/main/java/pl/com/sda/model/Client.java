package pl.com.sda.model;

import java.util.ArrayList;
import java.util.List;

public class Client {

    private String name;

    private String surname;

    private String login;

    private String password;

    private String address;

    private List<AbstractAccount> accounts;

    public Client(String name, String surname, String login, String password, String address, List<AbstractAccount> accounts) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.address = address;
        this.accounts = accounts;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<AbstractAccount> getAccounts() {

        if (accounts == null) {
            accounts = new ArrayList<>();
        }

        return accounts;
    }

    public void setAccounts(List<AbstractAccount> accounts) {
        this.accounts = accounts;
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", accounts=" + accounts +
                '}';
    }
}
