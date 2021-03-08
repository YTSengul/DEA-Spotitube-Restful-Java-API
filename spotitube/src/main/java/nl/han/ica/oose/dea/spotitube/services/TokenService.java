package nl.han.ica.oose.dea.spotitube.services;

import nl.han.ica.oose.dea.spotitube.dao.TokenDAO;

import javax.inject.Inject;

public class TokenService {

    private TokenDAO tokenDAO;

    public void updateToken(String username, String token) {
        tokenDAO.setUserToken(username, token);
    }

    public boolean checkToken(String token) {
        return tokenDAO.checkUserToken(token);
    }

    public String getUser(String token) {
        return tokenDAO.getUserFromToken(token);
    }

    @Inject
    public void setTokenDAO(TokenDAO tokendao) {
        this.tokenDAO = tokendao;
    }

}
