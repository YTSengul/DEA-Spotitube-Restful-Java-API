package nl.han.ica.oose.dea.spotitube.dao;

import nl.han.ica.oose.dea.spotitube.database.DatabaseConnect;
import nl.han.ica.oose.dea.spotitube.dto.*;

import javax.inject.Inject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlaylistDAO {

    private DatabaseConnect db;

    public PlaylistResponseDTO takeAllPlaylists(String username) {

        List<PlaylistDTO> playlistsDTOS = new ArrayList<>();
        int duration = 0;

        try {
            Connection connection = db.getCon();
            Statement stmt = connection.createStatement();
            String sql = "SELECT * FROM spotitube.playlists";
            ResultSet result = stmt.executeQuery(sql);
            connection.close();

            while (result.next()) {
                PlaylistDTO playlistDTO = new PlaylistDTO();
                int id = result.getInt("id");
                String name = result.getString("name").trim();
                String ownerName = result.getString("owner").trim();
                boolean owner = false;
                if (ownerName.equals(username)) {
                    owner = true;
                }
                playlistDTO.setId(id);
                playlistDTO.setName(name);
                playlistDTO.setOwner(owner);
                playlistDTO.setTracks(null);
                playlistsDTOS.add(playlistDTO);

                duration += getPlaylistLength(id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        PlaylistResponseDTO playlistResponseDTO = new PlaylistResponseDTO();
        playlistResponseDTO.setPlaylists(playlistsDTOS);
        playlistResponseDTO.setLength(duration);
        return playlistResponseDTO;
    }

    private int getPlaylistLength(int playlistid) {


        try {
            Connection connection = db.getCon();
            String sql = "select SUM(duration) as total_duration from spotitube.track where id IN(SELECT trackid from spotitube.playlistinside WHERE playlistid = ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, playlistid);
            ResultSet result = stmt.executeQuery();
            result.next();
            connection.close();
            return result.getInt("total_duration");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;

    }

    public void removePlaylistById(int playlistId) {


        try {
            Connection connection = db.getCon();
            String sql = "DELETE FROM spotitube.playlists WHERE id = ?;";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, playlistId);
            stmt.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void addPlayList(PlaylistRequestDTO playlistRequestDTO, String username) {


        try {
            Connection connection = db.getCon();
            String playlistName = playlistRequestDTO.getName();
            String sql = "INSERT INTO spotitube.playlists(name, owner) VALUES (?, ?);";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, playlistName);
            stmt.setString(2, username);
            stmt.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updatePlaylist(int playlistId, PlaylistRequestDTO playlistRequestDTO) {


        try {
            Connection connection = db.getCon();
            String newPlaylistName = playlistRequestDTO.getName();
            String sql = "UPDATE spotitube.playlists SET name = ? WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, newPlaylistName);
            stmt.setInt(2, playlistId);
            stmt.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void removeTrackInPlaylist(int playlistid, int trackid) {


        try {
            Connection connection = db.getCon();
            String sql = "DELETE FROM spotitube.playlistinside WHERE playlistId = ? AND trackid = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, playlistid);
            stmt.setInt(2, trackid);
            stmt.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public TrackResponseDTO takeAllTracksForPlaylist(int forPlaylist) {

        List<TrackDTO> trackDTOS = new ArrayList<>();

        try {
            Connection connection = db.getCon();
            String sql = "SELECT * FROM spotitube.track t INNER JOIN spotitube.playlistinside p ON t.id = p.trackid WHERE p.playlistid = ?;";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, forPlaylist);
            trackTakeInformation(trackDTOS, stmt, sql);
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new TrackResponseDTO(trackDTOS);

    }

    private void trackTakeInformation(List<TrackDTO> trackDTOS, PreparedStatement stmt, String sql) throws SQLException {
        ResultSet result = stmt.executeQuery();

        while (result.next()) {
            TrackDTO trackDTO = new TrackDTO();
            int id = result.getInt("id");
            String title = result.getString("title").trim();
            String performer = result.getString("performer").trim();
            int duration = result.getInt("duration");
            String album = "";
            if (result.getString("album") != null) {
                album = result.getString("album").trim();
            } else {
                album = "undefined";
            }
            int playcount = 0;
            if (result.getInt("playcount") > 0) {
                playcount = result.getInt("playcount");
            }
            String publicationDate = "";
            if (result.getString("publicationDate") != null) {
                publicationDate = result.getString("publicationDate").trim();
            } else {
                publicationDate = "undefined";
            }
            String description = "";
            if (result.getString("description") != null) {
                description = result.getString("description").trim();
            } else {
                description = "undefined";
            }
            boolean offlineAvailable = result.getBoolean("offlineAvailable");
            trackDTO.setId(id);
            trackDTO.setTitle(title);
            trackDTO.setPerformer(performer);
            trackDTO.setDuration(duration);
            trackDTO.setAlbum(album);
            trackDTO.setPlaycount(playcount);
            trackDTO.setPublicationDate(publicationDate);
            trackDTO.setDescription(description);
            trackDTO.setOfflineAvailable(offlineAvailable);
            trackDTOS.add(trackDTO);
        }
    }

    public void addTrackInsidePlaylist(int playlistId, int trackid) {

        try {
            Connection connection = db.getCon();
            String sql = "INSERT INTO spotitube.playlistinside (trackid, playlistid) VALUES (?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, trackid);
            stmt.setInt(2, playlistId);
            stmt.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateOfflineAvailableTrack(int playlistId, int trackId, boolean offlineAvailable) {

        try {
            Connection connection = db.getCon();
            String sql = "UPDATE spotitube.playlistinside SET \"offlineAvailable\"= ? WHERE playlistid = ? AND trackid = ?;";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setBoolean(1, offlineAvailable);
            stmt.setInt(2, playlistId);
            stmt.setInt(3, trackId);
            stmt.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Inject
    public void setDb(DatabaseConnect db) {
        this.db = db;
    }

}
