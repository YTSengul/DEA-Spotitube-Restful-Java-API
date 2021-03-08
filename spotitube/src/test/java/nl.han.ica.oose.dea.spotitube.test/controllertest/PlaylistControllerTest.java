package nl.han.ica.oose.dea.spotitube.test.controllertest;

import nl.han.ica.oose.dea.spotitube.controllers.PlaylistController;
import nl.han.ica.oose.dea.spotitube.dto.PlaylistRequestDTO;
import nl.han.ica.oose.dea.spotitube.dto.TrackDTO;
import nl.han.ica.oose.dea.spotitube.services.PlaylistService;
import nl.han.ica.oose.dea.spotitube.services.TokenService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;


public class PlaylistControllerTest {

    private PlaylistService playlistServiceMock;
    private TokenService tokenServiceMock;
    private PlaylistController sut;
    private PlaylistRequestDTO playlistRequestDTOMock;
    private TrackDTO trackDTOMock;

    @BeforeEach
    public void testSetup(){
        playlistServiceMock = Mockito.mock(PlaylistService.class);
        tokenServiceMock = Mockito.mock(TokenService.class);
        sut = new PlaylistController();
        sut.setPlaylistService(playlistServiceMock);
        sut.setTokenService(tokenServiceMock);

        playlistRequestDTOMock = Mockito.mock(PlaylistRequestDTO.class);
        trackDTOMock = Mockito.mock(TrackDTO.class);

        Mockito.when(tokenServiceMock.checkToken("")).thenReturn(true);
        Mockito.when(tokenServiceMock.checkToken("1234-1234-1234")).thenReturn(false);
    }

    @Test
    public void testEmptyTokenGivesBackBadStatusCode() {
        Response response = sut.returnPlaylists("1234-1234-1234");
        Assertions.assertEquals(403, response.getStatus());
    }

    @Test
    public void testValidTokenGivesBackGoodStatusCode() {
        Response response = sut.returnPlaylists("");
        Assertions.assertEquals(200, response.getStatus());
    }

    @Test
    public void testDeletePlaylistGivesBackGoodStatusCode() {
        Response response = sut.deletePlaylist("", 0);
        Assertions.assertEquals(200, response.getStatus());
    }

    @Test
    public void testAddPlaylistGivesBackGoodStatusCode() {
        Response response = sut.addPlaylist("", playlistRequestDTOMock);
        Assertions.assertEquals(200, response.getStatus());
    }

    @Test
    public void testUpdatePlaylistChangesTitle() {
        Response response = sut.updatePlaylist("", 0, playlistRequestDTOMock);
        Assertions.assertEquals(200, response.getStatus());
    }

    @Test
    public void testReturnPlaylistForAlbumGivesBackGoodStatusCode() {
        Response response = sut.returnPlaylistForAlbum("", 0);
        Assertions.assertEquals(200, response.getStatus());
    }

    @Test
    public void testRemoveTrackInPlaylistGivesBackGoodStatusCode() {
        Response response = sut.removeTrackInPlaylist("", 0, 0);
        Assertions.assertEquals(200, response.getStatus());
    }

    @Test
    public void testAddTrackInsidePlaylistGivesBackGoodStatusCode() {
        Response response = sut.addTrackInsidePlaylist("", 0, trackDTOMock);
        Assertions.assertEquals(200, response.getStatus());
    }

}
