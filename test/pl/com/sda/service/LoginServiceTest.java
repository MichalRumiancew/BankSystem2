package pl.com.sda.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import pl.com.sda.dao.ClientDAO;
import pl.com.sda.dao.ClientFileDAO;
import pl.com.sda.model.Client;

import java.util.Arrays;
import java.util.List;

public class LoginServiceTest {

    private LoginService loginService;

    private ClientDAO clientDAO;

    @Before
    public void before() {

        clientDAO = Mockito.mock(ClientFileDAO.class);
    }

    @Test
    public void testLoginPositive() {
        //given
        Client c1 = new Client("Jan", "Kowalski",
                "jkowalski", "1234", "Warszawa", null);

        Client c2 = new Client("Michał", "Nowak",
                "mnowak", "1234", "Kraków", null);


        List<Client> clients = Arrays.asList(c1, c2);

        Mockito.when(clientDAO.get())
                .thenReturn(clients);

        loginService = new LoginService(clientDAO);

        //when
        Client jkowalski = loginService.login("jkowalski", "1234");

        //then
        Assert.assertNotNull(jkowalski);
        Mockito.verify(clientDAO).get();
    }

}
