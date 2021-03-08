package nl.han.ica.oose.dea.spotitube.services;

import nl.han.ica.oose.dea.spotitube.dao.LoginDAO;

import javax.inject.Inject;

public class LoginService {

    private LoginDAO logindao;

    public boolean attemptLogin(String username, String password) {
        return logindao.tryToLogin(username, password);
    }

    @Inject
    public void setLogindao(LoginDAO logindao) {
        this.logindao = logindao;
    }

}
