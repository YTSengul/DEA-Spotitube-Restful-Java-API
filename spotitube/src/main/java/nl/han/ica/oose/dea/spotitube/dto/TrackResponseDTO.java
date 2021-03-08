package nl.han.ica.oose.dea.spotitube.dto;

import java.util.List;

public class TrackResponseDTO {

    private List<TrackDTO> tracks;

    public  TrackResponseDTO (List<TrackDTO> tracks) {
        this.tracks = tracks;
    }

    public void setTracks(List<TrackDTO> tracks) {
        this.tracks = tracks;
    }

    public List<TrackDTO> getTracks() {
        return tracks;
    }
}
