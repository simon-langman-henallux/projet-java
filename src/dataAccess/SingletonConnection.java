package dataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonConnection {
    private static Connection connectionUnique;

    private SingletonConnection() {}

    public static Connection getInstance() throws SQLException {
        if (connectionUnique == null || connectionUnique.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException("MySQL Driver non trouvé", e);
            }
            connectionUnique = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/db_game_store", "root", "root"
            );
        }
        return connectionUnique;
    }

    public static void closeConnection() throws SQLException {
        if (connectionUnique != null && !connectionUnique.isClosed()) {
            connectionUnique.close();
        }
    }
}