package nl.han.ica.oose.dea.spotitube.controllers;

import nl.han.ica.oose.dea.spotitube.dto.LoginRequestDTO;
import nl.han.ica.oose.dea.spotitube.dto.LoginResponseDTO;
import nl.han.ica.oose.dea.spotitube.services.LoginService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class LoginController {

    private LoginService loginService;
    private TokenGeneratorController tokenGeneratorController;

    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response returnLogin(LoginRequestDTO request)
    {
        String username = request.getUser();
        String password = request.getPassword();

        if(loginService.attemptLogin(username, password))
        {
            LoginResponseDTO response = new LoginResponseDTO();
            response.setToken(tokenGeneratorController.createToken(username));
            response.setUser(username);
            return  Response.ok().entity(response).build();
        }

        return Response.status(401).build();
    }

    @Inject
    public void setLoginService(LoginService loginService){
        this.loginService = loginService;
    }

    @Inject
    public void setTokenGeneratorController(TokenGeneratorController tokenGeneratorController) {
        this.tokenGeneratorController = tokenGeneratorController;
    }

}
