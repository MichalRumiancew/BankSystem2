package pl.com.sda.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.com.sda.exception.InsufficientBalanceException;
import pl.com.sda.exception.NegativeAmountException;
import pl.com.sda.model.AbstractAccount;
import pl.com.sda.model.CurrentAccount;
import pl.com.sda.model.SavingAccount;

public class AccountServiceTest {

    private AccountService underTest;

    @Before
    public void before() {
        underTest = new AccountService();
    }

    @Test
    public void shouldDoPaymentPositive() throws NegativeAmountException {

        //given
        AbstractAccount ac = new SavingAccount();
        ac.setBalance(100);

        double amount = 100;

        //when
        underTest.payment(ac, amount);

        //then
        double expected = ac.getBalance() + (amount * 0.01);
        Assert.assertEquals(expected, ac.getBalance(), 1.0);
    }


    @Test(expected = NegativeAmountException.class)
    public void shouldThrowExceptionWhenPayment() throws NegativeAmountException {

        //given
        AbstractAccount ac = new SavingAccount();
        ac.setBalance(100);

        double amount = -89;

        //when
        underTest.payment(ac, amount);

        //then
        //throw exception
    }

    @Test
    public void shouldDoPayOffPositive()
            throws NegativeAmountException, InsufficientBalanceException {

        //given
        AbstractAccount ac = new SavingAccount();
        ac.setBalance(100);

        double amount = 90;
        double balanceBeforePayoff = ac.getBalance();
        //when
        underTest.payOff(ac, amount);

        //then
        double expected = balanceBeforePayoff - amount;
        Assert.assertEquals(expected, ac.getBalance(), 1.0);
    }

    @Test
    public void shoudTransferAmount() throws InsufficientBalanceException, NegativeAmountException {
        //given
        AbstractAccount a1 = new CurrentAccount();
        AbstractAccount a2 = new SavingAccount();

        a1.setBalance(1000);
        double amount = 100;

        double balance1BeforeTransfer = a1.getBalance();
        double balance2BeforeTransfer = a2.getBalance();

        //when
        underTest.transfer(a1, a2, amount);

        //then
        double expected1 = balance1BeforeTransfer - amount;
        double expected2 = balance2BeforeTransfer +
                (amount * 0.01) + amount;

        Assert.assertEquals(expected1, a1.getBalance(), 1.0);
        Assert.assertEquals(expected2, a2.getBalance(), 1.0);

    }

}
