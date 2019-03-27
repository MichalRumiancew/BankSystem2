package pl.com.sda.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import pl.com.sda.dao.ClientDAO;
import pl.com.sda.dao.ClientFileDAO;
import pl.com.sda.model.AbstractAccount;
import pl.com.sda.model.Client;
import pl.com.sda.model.CurrentAccount;
import pl.com.sda.model.SavingAccount;

import java.util.Arrays;
import java.util.List;

public class BankServiceTest {

    private BankService bankService;

    private ClientDAO clientDAO;

    @Before
    public void before() {
        clientDAO = Mockito.mock(ClientFileDAO.class);
    }

    @Test
    public void testGetAccountByNumber() {
        //given
        Client c1 = new Client("Jan", "Kowalski",
                "jkowalski", "1234", "Warszawa", null);

        Client c2 = new Client("Michał", "Nowak",
                "mnowak", "1234", "Kraków", null);


        AbstractAccount c1a1 = new CurrentAccount("1", 10.4);
        AbstractAccount c1a2 = new SavingAccount("2", 3.0);

        AbstractAccount c2a1 = new CurrentAccount("3", 33.2);
        AbstractAccount c2a2 = new SavingAccount("4", 6);

        c1a1.setBalance(100);
        c2a2.setBalance(1005);


        c1.setAccounts(Arrays.asList(c1a1, c1a2));
        c2.setAccounts(Arrays.asList(c2a1, c2a2));

        List<Client> clients = Arrays.asList(c1, c2);

        ClientDAO clientDAO = Mockito.mock(ClientFileDAO.class);

        Mockito.when(clientDAO.get())
                .thenReturn(clients);

        bankService = new BankService(clientDAO);

        //when
        AbstractAccount accountByNumber = bankService.getAccountByNumber("1");

        //then
        Assert.assertNotNull(accountByNumber);
        Assert.assertEquals("1", accountByNumber.getNumber());
        Mockito.verify(clientDAO).get();
    }

}
