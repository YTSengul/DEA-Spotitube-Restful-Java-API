package nl.han.ica.oose.dea.spotitube.dao;

import nl.han.ica.oose.dea.spotitube.dto.TrackDTO;
import nl.han.ica.oose.dea.spotitube.dto.TrackResponseDTO;
import nl.han.ica.oose.dea.spotitube.database.DatabaseConnect;

import javax.inject.Inject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrackDAO {

    private DatabaseConnect db;

    public TrackResponseDTO takeAllTracksForNotPlaylist(int forNotPlaylist) {

        List<TrackDTO> trackDTOS = new ArrayList<>();

        try {
            Connection connection = db.getCon();
            String sql = "SELECT * FROM spotitube.track t LEFT JOIN spotitube.playlistinside p ON p.trackid = t.id AND p.playlistId = ? WHERE t.id NOT IN(SELECT trackId FROM spotitube.playlistinside where playlistID = ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, forNotPlaylist);
            stmt.setInt(2, forNotPlaylist);
            ResultSet result = stmt.executeQuery();
            connection.close();

            while (result.next()) {
                TrackDTO trackDTO = new TrackDTO();
                int id = result.getInt("id");
                String title = result.getString("title").trim();
                String performer = result.getString("performer").trim();
                int duration = result.getInt("duration");
                String album = "";
                if (result.getString("album") != null){
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

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new TrackResponseDTO(trackDTOS);

    }

    @Inject
    public void setDb(DatabaseConnect db) {
        this.db = db;
    }

}
