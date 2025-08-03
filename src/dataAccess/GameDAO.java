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
            throw new DataAccessException(e.getMessage());
        }
    }
    @Override
    public void insert(Game game) throws DataAccessException {
        String sql = "insert into game (title, price, releaseDate, description, ageRestriction, isMultiplayer, duration, stock, publisher, platform, genre) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, game.getTitle());
            ps.setDouble(2, game.getPrice());
            ps.setDate(3, (Date) game.getReleaseDate());
            ps.setObject(4, game.getDescription(), Types.VARCHAR);
            ps.setInt(5, game.getAgeRestriction());
            ps.setBoolean(6, game.isMultiplayer());
            ps.setObject(7, game.getDuration(), Types.DOUBLE);
            ps.setInt(8, game.getStock());
            ps.setString(9, game.getPublisher().toString());
            ps.setObject(10, game.getGenre().toString(), Types.VARCHAR);
            int affected = ps.executeUpdate();
            if (affected == 0) {
                throw new DataAccessException("Game insert failed.");
            }
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }
    @Override
    public void delete(String title) throws DataAccessException {
        String sql = "delete from game where title = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, title);
            int affected = ps.executeUpdate();
            if (affected == 0) {
                throw new DataAccessException("Game with title " + title + " not found");
            }
        }  catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }
    @Override
    public void update(Game game) throws DataAccessException {
        String sql = "update game set title=?, price=?, releaseDate=?, description=?, ageRestriction=?, isMultiplayer=?, duration=?, stock=?, publisher=?, platform=?, genre=? WHERE title=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, game.getTitle());
            ps.setDouble(2, game.getPrice());
            ps.setDate(3, new java.sql.Date(game.getReleaseDate().getTime()));
            ps.setString(4, game.getDescription());
            ps.setInt(5, game.getAgeRestriction());
            ps.setBoolean(6, game.isMultiplayer());
            ps.setDouble(7, game.getDuration());
            ps.setInt(8, game.getStock());
            ps.setString(9, game.getPublisher().toString());
            ps.setString(10, game.getGenre().toString());
            ps.setString(11, game.getTitle());
            int affected = ps.executeUpdate();
            if (affected == 0) {
                throw new DataAccessException("No rows affected.");
            }
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }
    @Override
    public Game getGameByTitle(String title) throws DataAccessException {
        String sql = "select * from game where title = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, title);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return map(rs);
                } else return null;
            }
        } catch  (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }
    @Override
    public List<Game> getAllGame() throws DataAccessException {
        List<Game> games = new ArrayList<>();
        String sql = "select * from game order by title";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                games.add(map(rs));
            }
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
        return games;
    }
    private Game map(ResultSet rs) throws DataAccessException {
        try {
            return new Game(
                    rs.getString("title"),
                    rs.getDouble("price"),
                    rs.getDate("releaseDate"),
                    rs.getString("description"),
                    rs.getInt("ageRestriction"),
                    rs.getBoolean("isMultiplayer"),
                    rs.getDouble("duration"),
                    rs.getInt("stock"),
                    rs.getString("publisher"),
                    rs.getString("platform"),
                    rs.getString("genre")
            );
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }

}