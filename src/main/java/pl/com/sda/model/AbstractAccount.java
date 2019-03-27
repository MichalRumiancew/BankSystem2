package pl.com.sda.model;

public abstract class AbstractAccount {

    protected String number;

    protected double balance;

    protected AccountType accountType;

    public AbstractAccount() {
        this.number = "" + System.nanoTime();
        this.balance = 0.0;
    }

    public String getNumber() {
        return number;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public AccountType getAccountType() {
        return accountType;
    }
}
