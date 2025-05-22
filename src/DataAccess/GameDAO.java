package DataAccess;

import java.sql.*;

public class GameDAO {

    public ResultSet ClientAdressByGameSearch(int idGame) {
        ResultSet resultSet = null;
        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT p.FirstName AS ClientFistName, p.Name AS ClientName, p.StreetName AS ClientStreetName,"+
                    "c.ZipCode AS CityZipCode, c.Name AS CityName, cy.Name AS CountryName, cy.Currency AS CountryCurrency" +
                    "FROM person p" +
                    "INNER JOIN document d ON d.Person = p.id"+
                    "INNER JOIN documentLine dl ON dl.Document = d.Reference" +
                    "INNER JOIN game g ON dl.Game = g.Title" +
                    "INNER JOIN city c ON p.ZipCodeCity = c.ZipCode AND p.NameCity = c.Name AND p.Country = c.Country" +
                    "INNER JOIN country cy ON c.Countrty = cy.Name" +
                    "WHERE g.Title = ? AND p.IsClient = 1"
            );

        }catch (SQLException e){
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet GameBoughtBeforeDateSearch(Date date){
        ResultSet resultSet = null;
        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT g.title AS GameTitle, d.CreationDate AS DateBought, dl.Quantity AS QuantityBought, dl.UnitPrice AS UnitPriceBought" +
                    "FROM game g" +
                    "INNER JOIN documentline dl ON dl.Game = g.title" +
                    "INNER JOIN document d ON dl.Document = d.Reference" +
                    "INNER JOIN person p ON d.Person = p.id" +
                    "WHERE c.id = ? AND d.date AFTER ?" +
                    "ORDER BY d.CreationDate ASC"
            );

    }catch (SQLException e){
        e.printStackTrace();
    }
        return resultSet;
    }
}
