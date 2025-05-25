package DataAccess;

import java.sql.*;
import java.util.ArrayList;
import Model.*;

import javax.swing.*;

public class GameDAO {

    public static Game getGameByData(ResultSet data){
        Game game = null;
        try {
            game = new Game();
            game.setTitle(data.getString("Title"));
            game.setPrice(data.getDouble("Price"));
            game.setReleaseDate(data.getDate("ReleaseDate"));
            game.setAgeRestriction(data.getInt("AgeRestriction"));
            game.setMultiplayer(data.getBoolean("IsMultiplayer"));

            String address = data.getString("Address");

            String description = data.getString("Description");
            if (!data.wasNull()){
                game.setDescription(description);
            }
            Double duration = data.getDouble("Duration");
            if (!data.wasNull()){
                game.setDuration(duration);
            }
            Platform platform = (Platform) data.getObject("Platform");
            if (!data.wasNull()){
                game.setPlatform(platform);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return game;
    }

    public static ArrayList<Game> getAll(){
        ArrayList<Game> games = new ArrayList<>();
        try {
            Connection connection = SingletonConnection.getInstance();
            String sqlInstruction = "SELECT * FROM game";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlInstruction);
            ResultSet data = preparedStatement.executeQuery();
            while (data.next()) {
                Game game = getGameByData(data);
                games.add(game);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return games;
    }



    public static ResultSet ClientAdressByGameSearch(String gameTitle) {
        ResultSet resultSet = null;
        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT p.FirstName AS clientFistName, p.Name AS clientName, p.StreetName AS clientStreetName,"+
                    "c.ZipCode AS cityZipCode, c.Name AS cityName, cy.Name AS countryName, cy.Currency AS countryCurrency" +
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
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT g.title AS gameTitle, d.CreationDate AS dateBought, dl.Quantity AS quantityBought, dl.UnitPrice AS unitPriceBought" +
                    "FROM game g, document d, documentline dl" +
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
