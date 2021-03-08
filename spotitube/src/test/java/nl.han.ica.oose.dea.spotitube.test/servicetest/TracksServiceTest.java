package nl.han.ica.oose.dea.spotitube.test.servicetest;

import nl.han.ica.oose.dea.spotitube.dto.TrackResponseDTO;
import nl.han.ica.oose.dea.spotitube.dao.TrackDAO;
import nl.han.ica.oose.dea.spotitube.services.TracksService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class TracksServiceTest {

    private TracksService tracksService;
    private TrackDAO tracksDAOMock;

    @BeforeEach
    public void testSetup(){
        tracksService =  new TracksService();
        tracksDAOMock = Mockito.mock(TrackDAO.class);
        tracksService.setTrackDAO(tracksDAOMock);
    }

    @Test
    public void testTakeAllTracksForNotPlaylist() {
        // Setup
        TrackResponseDTO trackResponseDTOMock = Mockito.mock(TrackResponseDTO.class);
        Mockito.when(tracksDAOMock.takeAllTracksForNotPlaylist(0)).thenReturn(trackResponseDTOMock);

        // Test
        TrackResponseDTO trackResponseDTO = tracksService.takeAllTracksForNotPlaylist(0);

        // Verify
        Assertions.assertEquals(trackResponseDTO, trackResponseDTOMock);
    }

}
