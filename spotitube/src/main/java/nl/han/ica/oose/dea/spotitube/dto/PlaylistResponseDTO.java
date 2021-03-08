package nl.han.ica.oose.dea.spotitube.dto;

import java.util.List;

public class PlaylistResponseDTO {

    private List<PlaylistDTO> playlists;
    private int length;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public List<PlaylistDTO> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<PlaylistDTO> playlists) {
        this.playlists = playlists;
    }

}
