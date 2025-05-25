package DataAccess;

import Model.Game;
import Model.Genre;
import Model.Platform;
import Model.Publisher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GameCRUD {

    public static void insertGame(Game game) throws SQLException {

        try (Connection con = SingletonConnection.getInstance()){
            String sql = "INSERT INTO game (Title, Price, ReleaseDate, Description, AgeRestriction, IsMultiplayer, Duration, Publisher, Genre, Platform) values(?,?,?,?,?,?,?,?,?,?)";
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
            String sql = "UPDATE GAME SET Title=?, Price=?, ReleaseDate=?, Description=?, AgeRestriction=?, IsMultiplayer=?, Duration=?, Publisher=?, Genre=?, Platform=? WHERE Title=?";
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
            String sql = "DELETE FROM game WHERE Title=?";
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
            String sql = "SELECT game.*, genre.Name AS genreName, genre.Description AS genreDescription " +
                    "FROM game " +
                    "JOIN genre ON game.Genre = genre.Name";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Game game = new Game();
                game.setTitle(rs.getString("Title"));
                game.setPrice(rs.getDouble("Price"));
                game.setReleaseDate(rs.getDate("ReleaseDate"));
                game.setDescription(rs.getString("Description"));
                game.setAgeRestriction(rs.getInt("AgeRestriction"));
                game.setMultiplayer(rs.getBoolean("IsMultiplayer"));
                game.setDuration(rs.getDouble("Duration"));
                Publisher publisher = new Publisher(rs.getString("Publisher"));
                game.setPublisher(publisher);
                Genre genre = new Genre(rs.getString("genreName"), rs.getString("genreDescription"));
                game.setGenre(genre);
                Platform platform = new Platform(rs.getString("Platform"));
                game.setPlatform(platform);
                games.add(game);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return games;
    }

}