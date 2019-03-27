package pl.com.sda.model;

public class CurrentAccount extends AbstractAccount {

    public CurrentAccount() {
        super();
        this.accountType = AccountType.CURRENT;
    }

    public CurrentAccount(String number, double balance) {
        this();
        this.number = number;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "CurrentAccount{" +
                "number='" + number + '\'' +
                ", balance=" + balance +
                ", accountType=" + accountType +
                '}';
    }
}
