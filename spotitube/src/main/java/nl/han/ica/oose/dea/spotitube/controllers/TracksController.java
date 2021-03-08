package nl.han.ica.oose.dea.spotitube.controllers;


import nl.han.ica.oose.dea.spotitube.services.TracksService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/tracks")
public class TracksController {

    private TracksService tracksService;

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response returnAllTracks(@QueryParam("token") String token, @QueryParam("forPlaylist") int forPlaylist) {
        return Response.ok().entity(tracksService.takeAllTracksForNotPlaylist(forPlaylist)).build();
    }

    @Inject
    public void setTracksService(TracksService tracksService) {
        this.tracksService = tracksService;
    }

}
