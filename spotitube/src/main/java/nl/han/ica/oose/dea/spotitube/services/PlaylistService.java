package nl.han.ica.oose.dea.spotitube.services;

import nl.han.ica.oose.dea.spotitube.dto.PlaylistRequestDTO;
import nl.han.ica.oose.dea.spotitube.dto.PlaylistResponseDTO;
import nl.han.ica.oose.dea.spotitube.dto.TrackResponseDTO;
import nl.han.ica.oose.dea.spotitube.dao.PlaylistDAO;

import javax.inject.Inject;

public class PlaylistService {

    private PlaylistDAO playlistDao;
    // Deze methode neemt alle playlists uit de database die er bestaan
    public PlaylistResponseDTO takeAllPlaylists(String user) {
        return playlistDao.takeAllPlaylists(user);
    }

    public void removePlaylist(int id) {
        playlistDao.removePlaylistById(id);
    }

    public void addPlaylist(PlaylistRequestDTO playlistRequestDTO, String username) {
        playlistDao.addPlayList(playlistRequestDTO, username);
    }

    public void updatePlaylist(int playlistId, PlaylistRequestDTO playlistRequestDTO) {
        playlistDao.updatePlaylist(playlistId, playlistRequestDTO);
    }

    public void removeTrackInPlaylist(int playlistid, int trackid) {
        playlistDao.removeTrackInPlaylist(playlistid, trackid);
    }

    public TrackResponseDTO takeAllTracksForPlaylist(int album) {
        return playlistDao.takeAllTracksForPlaylist(album);
    }

    public TrackResponseDTO addTrackInsidePlaylist(int playlistId, int trackId, boolean offlineAvailable) {
        playlistDao.addTrackInsidePlaylist(playlistId, trackId);
        playlistDao.updateOfflineAvailableTrack(playlistId, trackId, offlineAvailable);
        return playlistDao.takeAllTracksForPlaylist(playlistId);
    }

    @Inject
    public void setPlaylistDao(PlaylistDAO playlistDao) {
        this.playlistDao = playlistDao;
    }

}
