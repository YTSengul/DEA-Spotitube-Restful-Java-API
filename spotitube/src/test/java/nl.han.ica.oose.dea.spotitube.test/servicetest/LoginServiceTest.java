package nl.han.ica.oose.dea.spotitube.test.servicetest;

import nl.han.ica.oose.dea.spotitube.dao.LoginDAO;
import nl.han.ica.oose.dea.spotitube.services.LoginService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class LoginServiceTest {

    private LoginService loginService;
    private LoginDAO loginDAOMock;

    @BeforeEach
    public void testSetup(){
        loginService =  new LoginService();
        loginDAOMock = Mockito.mock(LoginDAO.class);
        loginService.setLogindao(loginDAOMock);
    }

    @Test
    public void testAttemptValidLogin() {
        Mockito.when(loginDAOMock.tryToLogin("admin","admin")).thenReturn(true);
        boolean testAttempt = loginService.attemptLogin("admin", "admin");
        Assertions.assertEquals(testAttempt, true);
    }

    @Test
    public void testAttemptInValidLogin() {
        Mockito.when(loginDAOMock.tryToLogin("admin","admin")).thenReturn(true);
        boolean testAttempt = loginService.attemptLogin("", "");
        Assertions.assertEquals(testAttempt, false);
    }

}
