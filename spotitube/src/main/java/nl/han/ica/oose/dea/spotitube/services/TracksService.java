package nl.han.ica.oose.dea.spotitube.services;

import nl.han.ica.oose.dea.spotitube.dto.TrackResponseDTO;
import nl.han.ica.oose.dea.spotitube.dao.TrackDAO;

import javax.inject.Inject;

public class TracksService {

    private TrackDAO trackDAO;

    public TrackResponseDTO takeAllTracksForNotPlaylist(int album) {
        return trackDAO.takeAllTracksForNotPlaylist(album);
    }

    @Inject
    public void setTrackDAO(TrackDAO trackDAO) {
        this.trackDAO = trackDAO;
    }

}
