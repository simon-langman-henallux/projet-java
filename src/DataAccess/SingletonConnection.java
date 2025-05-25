package DataAccess;

import java.sql.*;

public class SingletonConnection {
    private static Connection connectionUnique;
    private SingletonConnection(){}
    public static Connection getInstance() throws SQLException {
        if (connectionUnique == null || connectionUnique.isClosed()) {
            connectionUnique = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_projet", "root", "mdp1");
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