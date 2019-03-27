package pl.com.sda.model;

public class SavingAccount extends AbstractAccount {

    private double savingFactor;

    public SavingAccount() {
        super();
        this.accountType = AccountType.SAVING;
        this.savingFactor = 0.01;
    }

    public SavingAccount(String number, double balance) {
        this();
        this.number = number;
        this.balance = balance;
    }


    public double getSavingFactor() {
        return savingFactor;
    }

    @Override
    public String toString() {
        return "SavingAccount{" +
                "savingFactor=" + savingFactor +
                ", number='" + number + '\'' +
                ", balance=" + balance +
                ", accountType=" + accountType +
                '}';
    }
}
