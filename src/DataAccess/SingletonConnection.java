package DataAccess;

import java.sql.*;

public class SingletonConnection {
    private static Connection connectionUnique;
    private SingletonConnection(){}
    public static Connection getInstance() throws SQLException {
        if (connectionUnique == null || connectionUnique.isClosed()) {
            connectionUnique = DriverManager.getConnection("jdbc:mysql://PC-SIMON:3306/db_game_store", "root", "root");
        }
        return connectionUnique;
    }
    public static void closeConnection() throws SQLException {
        //Vérifier si la connexion est ouverte avant de la fermer
        if (connectionUnique != null && !connectionUnique.isClosed()) {
            connectionUnique.close();
        }
    }

}