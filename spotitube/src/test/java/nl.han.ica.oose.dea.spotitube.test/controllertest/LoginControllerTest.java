package nl.han.ica.oose.dea.spotitube.test.controllertest;

import nl.han.ica.oose.dea.spotitube.controllers.TokenGeneratorController;
import nl.han.ica.oose.dea.spotitube.controllers.LoginController;
import nl.han.ica.oose.dea.spotitube.dto.LoginRequestDTO;
import nl.han.ica.oose.dea.spotitube.services.LoginService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;


public class LoginControllerTest {

    private LoginService loginServiceMock;
    private TokenGeneratorController tokenGeneratorControllerMock;
    private LoginController sut = Mockito.mock(LoginController.class);

    @BeforeEach
    public void setupTest() {
        loginServiceMock = Mockito.mock(LoginService.class);
        tokenGeneratorControllerMock = Mockito.mock(TokenGeneratorController.class);
        sut = new LoginController();
        sut.setLoginService(loginServiceMock);
        sut.setTokenGeneratorController(tokenGeneratorControllerMock);
    }

    @Test
    public void testValidLoginUser() {
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO();
        loginRequestDTO.setUser("");
        loginRequestDTO.setPassword("");

        Mockito.when(loginServiceMock.attemptLogin("", "")).thenReturn(true);
        Mockito.when(tokenGeneratorControllerMock.createToken(loginRequestDTO.getUser())).thenReturn("ABCDEFGHIJKL");

        Response test = sut.returnLogin(loginRequestDTO);

        Assertions.assertEquals(200, test.getStatus());
    }

    @Test
    public void testInValidLoginUser() {
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO();
        loginRequestDTO.setUser("admin");
        loginRequestDTO.setPassword("admin");

        Mockito.when(loginServiceMock.attemptLogin(loginRequestDTO.getUser(), loginRequestDTO.getPassword())).thenReturn(false);

        Response test = sut.returnLogin(loginRequestDTO);

        Assertions.assertEquals(401, test.getStatus());
    }

}
