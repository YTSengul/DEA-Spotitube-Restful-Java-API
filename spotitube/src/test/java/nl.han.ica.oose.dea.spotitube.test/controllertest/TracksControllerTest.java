package nl.han.ica.oose.dea.spotitube.test.controllertest;

import nl.han.ica.oose.dea.spotitube.controllers.TracksController;
import nl.han.ica.oose.dea.spotitube.dto.TrackResponseDTO;
import nl.han.ica.oose.dea.spotitube.services.TracksService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;

public class TracksControllerTest {

    private TracksController sut;
    private TracksService tracksServiceMock;

    @BeforeEach
    public void testSetup(){
        sut = new TracksController();
        tracksServiceMock = Mockito.mock(TracksService.class);
        sut.setTracksService(tracksServiceMock);
        TrackResponseDTO trackResponseDTOMock = Mockito.mock(TrackResponseDTO.class);
        Mockito.when(tracksServiceMock.takeAllTracksForNotPlaylist(0)).thenReturn(trackResponseDTOMock);
    }

    @Test
    void testReturnAllTracks(){
        Response response = sut.returnAllTracks("", 0);
        Assertions.assertEquals(200, response.getStatus());
    }

}
