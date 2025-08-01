package DataAccess;

import java.sql.*;

public class SingletonConnection {
    private static Connection connectionUnique;
    private SingletonConnection(){}
    public static Connection getInstance() throws SQLException {
        if (connectionUnique == null || connectionUnique.isClosed()) {
            connectionUnique = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_game_store", "root", "root");
        }
        return connectionUnique;
    }
    public static void closeConnection() throws SQLException {
        if (connectionUnique != null && !connectionUnique.isClosed()) {
            connectionUnique.close();
        }
    }

}