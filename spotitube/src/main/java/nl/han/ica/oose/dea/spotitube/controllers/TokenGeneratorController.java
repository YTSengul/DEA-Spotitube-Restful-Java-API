package nl.han.ica.oose.dea.spotitube.controllers;

import nl.han.ica.oose.dea.spotitube.services.TokenService;

import javax.inject.Inject;
import java.util.Random;


public class TokenGeneratorController {

    private String token;
    private TokenService tokenService;

    public String createToken(String username) {
        this.token = "" + createDigits() + "-" + createDigits() + "-" + createDigits();
        tokenService.updateToken(username, token);
        return token;
    }

    private String createDigits() {
        int maxDigits = 8999;
        int correctDigits = 1000;
        int correctZero = 1;
        int returnValue = new Random().nextInt(maxDigits + correctZero) + correctDigits;
        return  "" + returnValue;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Inject
    public void setTokenService(TokenService tokenService) {
        this.tokenService = tokenService;
    }
}
