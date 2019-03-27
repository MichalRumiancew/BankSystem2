package pl.com.sda.service;

import pl.com.sda.exception.InsufficientBalanceException;
import pl.com.sda.exception.NegativeAmountException;
import pl.com.sda.model.AbstractAccount;
import pl.com.sda.model.AccountType;
import pl.com.sda.model.SavingAccount;

public class AccountService {

    public void payment(AbstractAccount account, double amount)
            throws NegativeAmountException {

        if (amount < 0.0) {
            throw new NegativeAmountException("Podana kwota jest ujemna!");
        }

        double newBalance = account.getBalance() + amount;
        account.setBalance(newBalance);

        if (account.getAccountType().equals(AccountType.SAVING)) {

            if (account instanceof SavingAccount) {
                SavingAccount sa = (SavingAccount) account;

                double newSavingBalance = (amount * sa.getSavingFactor())
                        + sa.getBalance();
                sa.setBalance(newSavingBalance);
            }
        }
    }


    public void payOff(AbstractAccount account, double amount)
            throws NegativeAmountException, InsufficientBalanceException {

        if (amount < 0.0) {
            throw new NegativeAmountException("Podana kwota jest ujemna!");
        }

        if (amount > account.getBalance()) {
            throw new InsufficientBalanceException("Podana kwota przewyższa stan konta!");
        }

        double newBalance = account.getBalance() - amount;
        account.setBalance(newBalance);
    }

    public void transfer(AbstractAccount src,
                         AbstractAccount dst,
                         double amount)
            throws NegativeAmountException, InsufficientBalanceException{


        payOff(src, amount);
        payment(dst, amount);

    }

}
