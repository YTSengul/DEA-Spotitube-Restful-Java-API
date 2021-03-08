package nl.han.ica.oose.dea.spotitube.dao;

import nl.han.ica.oose.dea.spotitube.database.DatabaseConnect;

import javax.inject.Inject;
import java.sql.*;

public class TokenDAO {

    @Inject
    private DatabaseConnect db;

    public void setUserToken(String username, String token) {
        try {
            Connection connection = db.getCon();
            String sql = "UPDATE spotitube.token SET token = ?  WHERE username = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, token);
            stmt.setString(2, username);
            stmt.executeQuery();
            connection.close();
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public boolean checkUserToken(String token) {
        try {
            Connection connection = db.getCon();
            String sql = "SELECT * FROM spotitube.token WHERE token = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, token);
            ResultSet result = stmt.executeQuery();
            connection.close();
            return result.next();
        } catch (SQLException e) {
            e.getMessage();
        }
        return false;
    }


    public String getUserFromToken(String token) {
        try {
            Connection connection = db.getCon();
            String sql = "SELECT username FROM spotitube.token WHERE token = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, token);
            ResultSet result = stmt.executeQuery();
            result.next();
            connection.close();
            // Hier wordt de trim functie gebruikt. Dit omdat in de database aangegeven is dat de string 255 karakters
            // lang kan zijn en die dus ook de volle 255 karakters terug stuurt.
            return result.getString("username").trim();
        } catch (SQLException e) {
            e.getMessage();
        }
        return null;
    }

}

