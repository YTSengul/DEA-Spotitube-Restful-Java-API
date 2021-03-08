package nl.han.ica.oose.dea.spotitube.controllers;

import nl.han.ica.oose.dea.spotitube.dto.PlaylistRequestDTO;
import nl.han.ica.oose.dea.spotitube.dto.TrackDTO;
import nl.han.ica.oose.dea.spotitube.services.PlaylistService;
import nl.han.ica.oose.dea.spotitube.services.TokenService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/playlists")
public class PlaylistController {

    private TokenService tokenService;
    private PlaylistService playlistService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response returnPlaylists(@QueryParam("token") String token) {
        if (!tokenService.checkToken(token)) {
            return Response.status(403).build();
        }
        String username = tokenService.getUser(token);
        return Response.ok().entity(playlistService.takeAllPlaylists(username)).build();
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deletePlaylist(@QueryParam("token") String token, @PathParam("id") int playlistId) {
        if (!tokenService.checkToken(token)) {
            return Response.status(403).build();
        }
        String username = tokenService.getUser(token);
        playlistService.removePlaylist(playlistId);
        return Response.ok().entity(playlistService.takeAllPlaylists(username)).build();
    }

    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePlaylist(@QueryParam("token") String token, @PathParam("id") int playlistId, PlaylistRequestDTO playlistRequestDTO) {
        if (!tokenService.checkToken(token)) {
            return Response.status(403).build();
        }
        String username = tokenService.getUser(token);
        playlistService.updatePlaylist(playlistId, playlistRequestDTO);
        return Response.ok().entity(playlistService.takeAllPlaylists(username)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPlaylist(@QueryParam("token") String token, PlaylistRequestDTO playlistRequestDTO){
        if (!tokenService.checkToken(token)) {
            return Response.status(403).build();
        }
        String username = tokenService.getUser(token);
        playlistService.addPlaylist(playlistRequestDTO, username);
        return Response.ok().entity(playlistService.takeAllPlaylists(username)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}/tracks")
    public Response returnPlaylistForAlbum(@QueryParam("token") String token, @PathParam("id") int albumId) {
        if (!tokenService.checkToken(token)) {
            return Response.status(403).build();
        }
        return Response.ok().entity(playlistService.takeAllTracksForPlaylist(albumId)).build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{pid}/tracks/{tid}")
    public Response removeTrackInPlaylist(@QueryParam("token") String token, @PathParam("pid") int playlistid, @PathParam("tid") int trackid) {
        if (!tokenService.checkToken(token)) {
            return Response.status(403).build();
        }
        playlistService.removeTrackInPlaylist(playlistid, trackid);
        return Response.ok().entity(playlistService.takeAllTracksForPlaylist(playlistid)).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}/tracks")
    public Response addTrackInsidePlaylist(@QueryParam("token") String token, @PathParam("id") int pid, TrackDTO trackDTO) {
        if (!tokenService.checkToken(token)) {
            return Response.status(403).build();
        }
        int trackId = trackDTO.getId();
        boolean offlineAvailable = trackDTO.isOfflineAvailable();
        return Response.ok().entity(playlistService.addTrackInsidePlaylist(pid, trackId, offlineAvailable)).build();
    }

    @Inject
    public void setTokenService(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Inject
    public void setPlaylistService(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

}
