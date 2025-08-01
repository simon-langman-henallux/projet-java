package dataAccess;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import exception.DataAccessException;
import model.Game;

public class GameDAO implements dataAccess.IGameDAO {

    private Connection conn;

    public GameDAO() throws DataAccessException {
        try {
            conn = dataAccess.SingletonConnection.getInstance();
        } catch (SQLException e) {
            throw new DataAccessException("Connections to Database Error");
        }
    }
    @Override
    public void insert(Game game) throws DataAccessException {
        String sql = "insert into game (title, price, releaseDate, description, ageRestriction, isMultiplayer, duration, publisher, platform, genre) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, game.getTitle());
            ps.setDouble(2, game.getPrice());
            ps.setDate(3, (Date) game.getReleaseDate());
            ps.setString(4, game.getDescription());
            ps.setInt(5, game.getAgeRestriction());
            ps.setBoolean(6, game.isMultiplayer());
            ps.setDouble(7, game.getDuration());
            ps.setString(8, game.getPublisher().toString());
            ps.setString(9, game.getGenre().toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Insert Game Error");
        }
    }
    @Override
    public void delete(String title) throws DataAccessException {
        String sql = "delete from game where title = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, title);
            ps.executeUpdate();
        }  catch (SQLException e) {
            throw new DataAccessException("Delete Game Error");
        }
    }
    @Override
    public void update(Game game) throws DataAccessException {
        String sql = "update game set title=?, price=?, releaseDate=?, description=?, ageRestriction=?, isMultiplayer=?, duration=?, publisher=?, platform=?, genre=? WHERE title=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, game.getTitle());
            ps.setDouble(2, game.getPrice());
            ps.setDate(3, (Date) game.getReleaseDate());
            ps.setString(4, game.getDescription());
            ps.setInt(5, game.getAgeRestriction());
            ps.setBoolean(6, game.isMultiplayer());
            ps.setDouble(7, game.getDuration());
            ps.setString(8, game.getPublisher().toString());
            ps.setString(9, game.getGenre().toString());
            ps.setString(10, game.getTitle());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Update Game Error");
        }
    }
    @Override
    public Game findById(String title) throws DataAccessException {
        String sql = "select * from game where title = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, title);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return map(rs);
                } else return null;
            }
        } catch  (SQLException e) {
            throw new DataAccessException("Find Game by Title Error");
        }
    }
    @Override
    public List<Game> findAll() throws DataAccessException {
        List<Game> games = new ArrayList<>();
        String sql = "SELECT * FROM game";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                games.add(map(rs));
            }
        } catch (SQLException e) {
            throw new DataAccessException("findAll Error");
        }
        return games;
    }
    private Game map(ResultSet rs) throws SQLException {
        return new Game(
                rs.getString("title"),
                rs.getDouble("price"),
                rs.getDate("releaseDate"),
                rs.getString("description"),
                rs.getInt("ageRestriction"),
                rs.getBoolean("isMultiplayer"),
                rs.getDouble("duration"),
                rs.getString("publisher"),
                rs.getString("platform"),
                rs.getString("genre")
        );
    }
}