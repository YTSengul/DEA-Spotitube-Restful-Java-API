package nl.han.ica.oose.dea.spotitube.dao;

import nl.han.ica.oose.dea.spotitube.database.DatabaseConnect;

import javax.inject.Inject;
import java.sql.*;

public class LoginDAO {

    @Inject
    private DatabaseConnect db;

    public boolean tryToLogin(String username, String password) {


        try {
            Connection connection = db.getCon();
            String sql = "SELECT * FROM spotitube.users WHERE username = ? AND password = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet result = stmt.executeQuery();
            connection.close();
            result.next();
            String takeBackUser = result.getString("username").trim();
            if(username.equals(takeBackUser)) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }

}
