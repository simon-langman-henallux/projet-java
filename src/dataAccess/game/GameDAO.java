package dataAccess.game;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import exception.DataAccessException;
import model.Game;

public class GameDAO implements IGameDAO {

    private final Connection conn;

    public GameDAO(Connection conn) {
        this.conn = conn;
    }

    public void insert(Game game) throws DataAccessException {
        String sql = "insert into game (title, price, releaseDate, description, ageRestriction, isMultiplayer, duration, stock, publisher, platform, genre) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, game.getTitle());
            ps.setDouble(2, game.getPrice());
            ps.setDate(3, new Date(game.getReleaseDate().getTime()));
            ps.setObject(4, game.getDescription(), Types.VARCHAR);
            ps.setInt(5, game.getAgeRestriction());
            ps.setBoolean(6, game.isMultiplayer());
            ps.setObject(7, game.getDuration(), Types.DOUBLE);
            ps.setInt(8, game.getStock());
            ps.setString(9, game.getPublisher());
            ps.setString(10, game.getPlatform());
            ps.setObject(11, game.getGenre(), Types.VARCHAR);
            if (ps.executeUpdate() == 0) throw new DataAccessException("Game insert failed.");
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    public void delete(String title) throws DataAccessException {
        String sql = "delete from game where title = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, title);
            if (ps.executeUpdate() == 0) throw new DataAccessException("Game with title " + title + " not found");
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    public void update(Game game, String originalTitle) throws DataAccessException {
        String sql = "update game set title=?, price=?, releaseDate=?, description=?, ageRestriction=?, isMultiplayer=?, duration=?, stock=?, publisher=?, platform=?, genre=? where title=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, game.getTitle());
            ps.setDouble(2, game.getPrice());
            ps.setDate(3, new Date(game.getReleaseDate().getTime()));
            ps.setObject(4, game.getDescription(), Types.VARCHAR);
            ps.setInt(5, game.getAgeRestriction());
            ps.setBoolean(6, game.isMultiplayer());
            ps.setObject(7, game.getDuration(), Types.DOUBLE);
            ps.setInt(8, game.getStock());
            ps.setString(9, game.getPublisher());
            ps.setString(10, game.getPlatform());
            ps.setString(11, game.getGenre());
            ps.setString(12, originalTitle);
            if (ps.executeUpdate() == 0) throw new DataAccessException("No rows affected.");
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    public Game getGameByTitle(String title) throws DataAccessException {
        String sql = "select * from game where title = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, title);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return map(rs);
                else return null;
            }
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    public List<Game> getAllGame() throws DataAccessException {
        List<Game> games = new ArrayList<>();
        String sql = "select * from game order by title";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) games.add(map(rs));
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
        return games;
    }

    public List<String> getAllPublisherNames() throws DataAccessException {
        List<String> result = new ArrayList<>();
        String sql = "select name from publisher order by name";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) result.add(rs.getString("name"));
        } catch (SQLException e) {
            throw new DataAccessException("Error loading publishers: " + e.getMessage());
        }
        return result;
    }

    public List<String> getAllPlatformNames() throws DataAccessException {
        List<String> result = new ArrayList<>();
        String sql = "select name from platform order by name";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) result.add(rs.getString("name"));
        } catch (SQLException e) {
            throw new DataAccessException("Error loading platforms: " + e.getMessage());
        }
        return result;
    }

    public List<String> getAllGenreNames() throws DataAccessException {
        List<String> result = new ArrayList<>();
        String sql = "select name from genre order by name";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) result.add(rs.getString("name"));
        } catch (SQLException e) {
            throw new DataAccessException("Error loading genres: " + e.getMessage());
        }
        return result;
    }

    public boolean hasRelatedDocumentLines(String title) throws DataAccessException {
        String sql = "select count(*) from orderLine where game = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, title);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
        return false;
    }

    public void deleteWithDocumentLines(String title) throws DataAccessException {
        try {
            conn.setAutoCommit(false);
            try (PreparedStatement ps1 = conn.prepareStatement("delete from orderLine where game = ?")) {
                ps1.setString(1, title);
                ps1.executeUpdate();
            }
            try (PreparedStatement ps2 = conn.prepareStatement("delete from game where title = ?")) {
                ps2.setString(1, title);
                ps2.executeUpdate();
            }
            conn.commit();
        } catch (SQLException e) {
            try { conn.rollback(); } catch (SQLException ignore) {}
            throw new DataAccessException(e.getMessage());
        } finally {
            try { conn.setAutoCommit(true); } catch (SQLException ignore) {}
        }
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