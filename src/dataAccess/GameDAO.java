package dataAccess;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import exception.DataAccessException;
import model.Game;

public class GameDAO implements dataAccess.IGameDAO {

    private final Connection conn;

    public GameDAO() throws DataAccessException {
        try {
            conn = dataAccess.SingletonConnection.getInstance();
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }
    @Override
    public void insert(Game game) throws DataAccessException {
        String sql = "insert into game (title, price, releaseDate, description, ageRestriction, isMultiplayer, duration, stock, publisher, platform, genre) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, game.getTitle());
            ps.setDouble(2, game.getPrice());
            ps.setDate(3, new java.sql.Date(game.getReleaseDate().getTime()));
            ps.setObject(4, game.getDescription(), Types.VARCHAR);
            ps.setInt(5, game.getAgeRestriction());
            ps.setBoolean(6, game.isMultiplayer());
            ps.setObject(7, game.getDuration(), Types.DOUBLE);
            ps.setInt(8, game.getStock());
            ps.setString(9, game.getPublisher());
            ps.setString(10, game.getPlatform());
            ps.setObject(11, game.getGenre(), Types.VARCHAR);
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
    public void update(Game game, String originalTitle) throws DataAccessException {
        String sql = "UPDATE game SET title=?, price=?, releaseDate=?, description=?, ageRestriction=?, isMultiplayer=?, duration=?, stock=?, publisher=?, platform=?, genre=? WHERE title=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, game.getTitle());
            ps.setDouble(2, game.getPrice());
            ps.setDate(3, new java.sql.Date(game.getReleaseDate().getTime()));
            ps.setObject(4, game.getDescription(), Types.VARCHAR);
            ps.setInt(5, game.getAgeRestriction());
            ps.setBoolean(6, game.isMultiplayer());
            ps.setObject(7, game.getDuration(), Types.DOUBLE);
            ps.setInt(8, game.getStock());
            ps.setString(9, game.getPublisher());
            ps.setString(10, game.getPlatform());
            ps.setString(11, game.getGenre());
            ps.setString(12, originalTitle);
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

    public List<String> getAllPublisherNames() throws DataAccessException {
        List<String> result = new ArrayList<>();
        String sql = "SELECT name FROM publisher ORDER BY name";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                result.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error loading publishers: " + e.getMessage());
        }
        return result;
    }

    public List<String> getAllPlatformNames() throws DataAccessException {
        List<String> result = new ArrayList<>();
        String sql = "SELECT name FROM platform ORDER BY name";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                result.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error loading platforms: " + e.getMessage());
        }
        return result;
    }

    public List<String> getAllGenreNames() throws DataAccessException {
        List<String> result = new ArrayList<>();
        String sql = "SELECT name FROM genre ORDER BY name";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                result.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error loading genres: " + e.getMessage());
        }
        return result;
    }

    @Override
    public Map<String, BigDecimal> getTotalSalesByGenre() throws DataAccessException {
        String sql = "select g.genre, sum(dl.quantity * dl.unitPrice) as total from documentline dl join game g on g.title = dl.game join document d on d.reference = dl.document where d.type = 'Sale' group by g.genre";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            Map<String, BigDecimal> result = new java.util.HashMap<>();
            while (rs.next()) {
                result.put(rs.getString("genre"), rs.getBigDecimal("total"));
            }
            return result;

        } catch (SQLException e) {
            throw new DataAccessException("Error retrieving total sales by genre: " + e.getMessage());
        }
    }

    @Override
    public Map<String, BigDecimal> getAveragePriceByPublisher() throws DataAccessException {
        String sql = "select publisher, avg(price) as average from game group by publisher";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            Map<String, BigDecimal> result = new java.util.HashMap<>();
            while (rs.next()) {
                result.put(rs.getString("publisher"), rs.getBigDecimal("average"));
            }
            return result;

        } catch (SQLException e) {
            throw new DataAccessException("Error retrieving average price by publisher: " + e.getMessage());
        }
    }

    @Override
    public boolean hasRelatedDocumentLines(String title) throws DataAccessException {
        String sql = "SELECT COUNT(*) FROM documentLine WHERE game = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, title);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
        return false;
    }

    @Override
    public void deleteWithDocumentLines(String title) throws DataAccessException {
        try {
            conn.setAutoCommit(false);

            try (PreparedStatement ps1 = conn.prepareStatement("DELETE FROM documentLine WHERE game = ?")) {
                ps1.setString(1, title);
                ps1.executeUpdate();
            }

            try (PreparedStatement ps2 = conn.prepareStatement("DELETE FROM game WHERE title = ?")) {
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