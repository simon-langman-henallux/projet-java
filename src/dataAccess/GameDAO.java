package dataAccess;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import exception.DataAccessException;
import model.Game;

public class GameDAO implements IGameDAO {

    private Connection conn;

    public void insert(Game game) throws SQLException {
        try {
            conn = dataAccess.SingletonConnection.getInstance();
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }

    }
    public Game find(String title) throws SQLException { return null; }
    public List<Game> findAll() throws SQLException { return null; }
    public void update(Game game) throws SQLException {}
    public void delete(String title) throws SQLException {}
}

/*
package dataAccess;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exception.DataAccessException;
import model.Game;

public class GameDAO implements IGameDAO {
    private final Connection conn;

    public GameDAO() throws DataAccessException {
        try {
            this.conn = SingletonConnection.getInstance();
        } catch (SQLException e) {
            throw new DataAccessException("Erreur de connexion à la base de données", e);
        }
    }

    @Override
    public void insert(Game game) throws DataAccessException {
        String sql = "INSERT INTO game (title, price, releaseDate, description, ageRestriction, isMultiplayer, duration, publisher, platform, genre) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, game.getTitle());
            ps.setDouble(2, game.getPrice());
            ps.setDate(3, Date.valueOf(game.getReleaseDate()));
            ps.setString(4, game.getDescription());
            ps.setInt(5, game.getAgeRestriction());
            ps.setBoolean(6, game.isMultiplayer());
            if (game.getDuration() != null) {
                ps.setDouble(7, game.getDuration());
            } else {
                ps.setNull(7, java.sql.Types.DOUBLE);
            }
            ps.setString(8, game.getPublisher());
            ps.setString(9, game.getPlatform());
            if (game.getGenre() != null) {
                ps.setString(10, game.getGenre());
            } else {
                ps.setNull(10, java.sql.Types.VARCHAR);
            }
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Erreur lors de l'insertion d'un jeu", e);
        }
    }

    @Override
    public void update(Game game) throws DataAccessException {
        String sql = "UPDATE game SET price=?, releaseDate=?, description=?, ageRestriction=?, isMultiplayer=?, duration=?, publisher=?, platform=?, genre=? WHERE title=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, game.getPrice());
            ps.setDate(2, Date.valueOf(game.getReleaseDate()));
            ps.setString(3, game.getDescription());
            ps.setInt(4, game.getAgeRestriction());
            ps.setBoolean(5, game.isMultiplayer());
            if (game.getDuration() != null) {
                ps.setDouble(6, game.getDuration());
            } else {
                ps.setNull(6, java.sql.Types.DOUBLE);
            }
            ps.setString(7, game.getPublisher());
            ps.setString(8, game.getPlatform());
            if (game.getGenre() != null) {
                ps.setString(9, game.getGenre());
            } else {
                ps.setNull(9, java.sql.Types.VARCHAR);
            }
            ps.setString(10, game.getTitle());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Erreur lors de la mise à jour du jeu", e);
        }
    }

    @Override
    public void delete(String title) throws DataAccessException {
        String sql = "DELETE FROM game WHERE title = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, title);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Erreur lors de la suppression du jeu", e);
        }
    }

    @Override
    public Game findById(String title) throws DataAccessException {
        String sql = "SELECT * FROM game WHERE title = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, title);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return map(rs);
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException("Erreur lors de la recherche du jeu", e);
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
            throw new DataAccessException("Erreur lors de la récupération des jeux", e);
        }
        return games;
    }

    private Game map(ResultSet rs) throws SQLException {
        return new Game(
            rs.getString("title"),
            rs.getDouble("price"),
            rs.getDate("releaseDate").toLocalDate(),
            rs.getString("description"),
            rs.getInt("ageRestriction"),
            rs.getBoolean("isMultiplayer"),
            rs.getObject("duration") != null ? rs.getDouble("duration") : null,
            rs.getString("publisher"),
            rs.getString("platform"),
            rs.getString("genre")
        );
    }
}
*/