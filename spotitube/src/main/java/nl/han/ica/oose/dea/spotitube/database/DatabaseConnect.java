package nl.han.ica.oose.dea.spotitube.database;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnect {

    private Connection con;
    private Properties properties;

    public DatabaseConnect() {
        connect();
    }

    public void connect() {

        if(properties == null) {
            properties = getProperties();
        }

        try {
            Class.forName(properties.getProperty("driver"));
            } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getCon() throws SQLException {
        return DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("user"), properties.getProperty("password"));
    }

    public Properties getProperties() {
        Properties properties = new Properties();
        try {
            properties.load(getClass().getResourceAsStream("/database.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

}
