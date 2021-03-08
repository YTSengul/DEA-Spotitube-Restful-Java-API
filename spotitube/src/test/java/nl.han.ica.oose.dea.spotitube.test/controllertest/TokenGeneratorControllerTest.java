package nl.han.ica.oose.dea.spotitube.test.controllertest;

import nl.han.ica.oose.dea.spotitube.controllers.TokenGeneratorController;
import nl.han.ica.oose.dea.spotitube.services.TokenService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class TokenGeneratorControllerTest {

    private TokenGeneratorController tokenGeneratorController;
    private TokenService tokenServiceMock;

    @BeforeEach
    public void testSetup() {
        tokenGeneratorController = new TokenGeneratorController();
        tokenServiceMock = Mockito.mock(TokenService.class);
        tokenGeneratorController.setTokenService(tokenServiceMock);
    }

    @Test
    public void checkIfTokenGeneratorGenertesValidToken() {
        String testStringToken = tokenGeneratorController.createToken("");
        Assertions.assertEquals(testStringToken, tokenGeneratorController.getToken());
    }

}
