package DataAccess;

import java.sql.*;
import java.util.ArrayList;
import Model.*;

public class GameDAO {

    public static Game getGameByData(ResultSet data){
        Game game = null;
        try {
            game = new Game();
            game.setTitle(data.getString("title"));
            game.setPrice(data.getDouble("price"));
            game.setReleaseDate(data.getDate("date"));
            game.setAgeRestriction(data.getInt("ageRestriction"));
            game.setMultiplayer(data.getBoolean("isMultiplayer"));

            String address = data.getString("Address");

            String description = data.getString("description");
            if (!data.wasNull()){
                game.setDescription(description);
            }
            Double duration = data.getDouble("duration");
            if (!data.wasNull()){
                game.setDuration(duration);
            }
            Platform platform = (Platform) data.getObject("platform");
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
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT p.firstName AS clientFirstName, p.name AS clientName, p.streetName AS clientStreetName, " +
                            "c.zipCode AS cityZipCode, c.name AS cityName, cy.name AS countryName, cy.currency AS countryCurrency " +
                            "FROM person p " +
                            "INNER JOIN document d ON d.person = p.id " +
                            "INNER JOIN documentLine dl ON dl.document = d.reference " +
                            "INNER JOIN game g ON dl.game = g.title " +
                            "INNER JOIN city c ON p.zipCodeCity = c.zipCode AND p.nameCity = c.name AND p.country = c.country " +
                            "INNER JOIN country cy ON c.country = cy.name " +
                            "WHERE g.title = ? AND p.isClient = 1"
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
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT g.title AS gameTitle, d.date AS dateBought, dl.quantity AS quantityBought, dl.unitPrice AS unitPriceBought " +
                            "FROM game g " +
                            "INNER JOIN documentLine dl ON dl.game = g.title " +
                            "INNER JOIN document d ON dl.document = d.reference " +
                            "INNER JOIN person p ON d.person = p.id " +
                            "WHERE p.id = ? AND d.date > ? " +
                            "ORDER BY d.date ASC"
            );

    }catch (SQLException e){
        e.printStackTrace();
    }
        return resultSet;
    }

    public static void insertGame(Game game) throws SQLException {

        try (Connection con = SingletonConnection.getInstance()){
            String sql = "INSERT INTO game (title, price, releaseDate, description, ageRestriction, isMultiplayer, duration, Publisher, Genre, Platform) values(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, game.getTitle());
            ps.setDouble(2, game.getPrice());
            ps.setDate(3, new java.sql.Date(game.getReleaseDate().getTime()));
            ps.setString(4, game.getDescription());
            ps.setInt(5, game.getAgeRestriction());
            ps.setBoolean(6, game.isMultiplayer());
            ps.setDouble(7, game.getDuration());
            ps.setString(8, game.getPublisher().getName());
            ps.setString(9, game.getGenre().getName());
            ps.setString(10, game.getPlatform().getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateGame(Game game) throws SQLException {
        try (Connection con = SingletonConnection.getInstance()){
            String sql = "UPDATE game SET title=?, price=?, releaseDate=?, description=?, ageRestriction=?, isMultiplayer=?, duration=?, Publisher=?, Genre=?, Platform=? WHERE title=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, game.getTitle());
            ps.setDouble(2, game.getPrice());
            ps.setDate(3, new java.sql.Date(game.getReleaseDate().getTime()));
            ps.setString(4, game.getDescription());
            ps.setInt(5, game.getAgeRestriction());
            ps.setBoolean(6, game.isMultiplayer());
            ps.setDouble(7, game.getDuration());
            ps.setString(8, game.getPublisher().getName());
            ps.setString(9, game.getGenre().getName());
            ps.setString(10, game.getPlatform().getName());
            ps.setString(11, game.getTitle());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteGame(Game game) throws SQLException {
        try (Connection con = SingletonConnection.getInstance()){
            String sql = "DELETE FROM game WHERE title=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, game.getTitle());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Game> listAllGames() throws SQLException {
        ArrayList<Game> games = new ArrayList<>();
        try (Connection con = SingletonConnection.getInstance()){
            String sql = "SELECT Game.*, genre.name AS genreName, genre.description AS genreDescription " +
                    "FROM Game " +
                    "JOIN genre ON Game.Genre = genre.name";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Game Game = new Game();
                Game.setTitle(rs.getString("title"));
                Game.setPrice(rs.getDouble("price"));
                Game.setReleaseDate(rs.getDate("date"));
                Game.setDescription(rs.getString("description"));
                Game.setAgeRestriction(rs.getInt("ageRestriction"));
                Game.setMultiplayer(rs.getBoolean("isMultiplayer"));
                Game.setDuration(rs.getDouble("duration"));
                Publisher publisher = new Publisher(rs.getString("Publisher"));
                Game.setPublisher(publisher);
                Genre genre = new Genre(rs.getString("genreName"), rs.getString("genreDescription"));
                Game.setGenre(genre);
                Platform platform = new Platform(rs.getString("Platform"));
                Game.setPlatform(platform);
                games.add(Game);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return games;
    }

}
