package nl.han.ica.oose.dea.spotitube.test.servicetest;

import nl.han.ica.oose.dea.spotitube.dto.PlaylistRequestDTO;
import nl.han.ica.oose.dea.spotitube.dto.PlaylistResponseDTO;
import nl.han.ica.oose.dea.spotitube.dto.TrackResponseDTO;
import nl.han.ica.oose.dea.spotitube.dao.PlaylistDAO;
import nl.han.ica.oose.dea.spotitube.services.PlaylistService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

import java.util.ArrayList;

public class PlaylistServiceTest {

    private PlaylistService playlistService;
    private PlaylistDAO playlistDAOMock;

    @BeforeEach
    public void testSetup(){
        playlistService =  new PlaylistService();
        playlistDAOMock = Mockito.mock(PlaylistDAO.class);
        playlistService.setPlaylistDao(playlistDAOMock);
    }

    @Test
    public void testTakeAllPlaylists(){
        PlaylistResponseDTO playlistResponseDTO = new PlaylistResponseDTO();
        Mockito.when(playlistService.takeAllPlaylists(Mockito.any())).thenReturn(playlistResponseDTO);
        Assertions.assertEquals(playlistService.takeAllPlaylists(Mockito.any()), playlistResponseDTO);
    }

    @Test
    public void testTakeAllTracksForPlaylist() {
        TrackResponseDTO trackResponseDTO = new TrackResponseDTO(new ArrayList<>());
        Mockito.when(playlistService.takeAllTracksForPlaylist(0)).thenReturn(trackResponseDTO);
        Assertions.assertEquals(playlistService.takeAllTracksForPlaylist(0), trackResponseDTO);
    }

    @Test
    public void testAddTrackInsidePlaylist() {
        TrackResponseDTO trackResponseDTO = new TrackResponseDTO(new ArrayList<>());
        Mockito.when(playlistService.addTrackInsidePlaylist(0,0,true)).thenReturn(trackResponseDTO);
        Assertions.assertEquals((playlistService.addTrackInsidePlaylist(0,0,true)), trackResponseDTO);
    }

    @Test
    public void testRemovePlaylist(){
        // Setup

        // Test
        playlistService.removePlaylist(0);
        // Verify
        verify(playlistDAOMock).removePlaylistById(0);
    }

    @Test
    public void testAddPlaylist(){
        // Setup
        PlaylistRequestDTO playlistRequestDTOMock = Mockito.mock(PlaylistRequestDTO.class);
        // Test
        playlistService.addPlaylist(playlistRequestDTOMock, "");
        //Verify
        verify(playlistDAOMock).addPlayList(playlistRequestDTOMock, "");
    }

    @Test
    public void testUpdatePlaylist(){
        // Setup
        PlaylistRequestDTO playlistRequestDTOMock = Mockito.mock(PlaylistRequestDTO.class);
        // Test
        playlistService.updatePlaylist(0, playlistRequestDTOMock);
        //Verify
        verify(playlistDAOMock).updatePlaylist(0, playlistRequestDTOMock);
    }



    @Test
    public void removeUpdatePlaylist(){
        // Setup

        // Test
        playlistService.removeTrackInPlaylist(0, 0);
        //Verify
        verify(playlistDAOMock).removeTrackInPlaylist(0, 0);
    }

}
