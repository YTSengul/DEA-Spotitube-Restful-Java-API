package nl.han.ica.oose.dea.spotitube.test.servicetest;

import nl.han.ica.oose.dea.spotitube.dao.TokenDAO;
import nl.han.ica.oose.dea.spotitube.services.TokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class TokenServiceTest {

    private TokenService tokenService;
    private TokenDAO tokenDAOMock;

    @BeforeEach
    void testSetup(){
        tokenService = new TokenService();
        tokenDAOMock = Mockito.mock(TokenDAO.class);
        tokenService.setTokenDAO(tokenDAOMock);
    }

    @Test
    void testUpdateToken(){
        // Setup


        // Test
        tokenService.updateToken("","");

        // Verify
        verify(tokenDAOMock).setUserToken("", "");
    }

    @Test
    void testCheckToken(){
        // Setup


        // Test
        tokenService.checkToken("");

        // Verify
        verify(tokenDAOMock).checkUserToken("");
    }

    @Test
    void testGetUser(){
        // Setup


        // Test
        tokenService.getUser("");

        // Verify
        verify(tokenDAOMock).getUserFromToken("");
    }

}
