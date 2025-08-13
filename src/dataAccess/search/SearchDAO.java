package dataAccess.search;

import exception.DataAccessException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SearchDAO implements ISearchDAO {

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/db_game_store", "root", "root");
    }

    public List<Object[]> getCountries() throws DataAccessException {
        String sql = "select name from country";
        List<Object[]> out = new ArrayList<>();
        try (Connection cn = getConnection();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                out.add(new Object[]{rs.getString(1)});
            }
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
        return out;
    }

    public List<Object[]> searchByGameTitle(String title) throws DataAccessException {
        String sql = "select p.firstName, p.name, p.streetName, c.zipCode, c.name, co.name, co.currency " +
                "from person p " +
                "join city c on p.zipCodeCity = c.zipCode and p.nameCity = c.name and p.country = c.country " +
                "join country co on c.country = co.name " +
                "join document d on p.id = d.person " +
                "join documentLine dl on d.reference = dl.document " +
                "join game g on dl.game = g.title " +
                "where g.title = ?";
        List<Object[]> out = new ArrayList<>();
        try (Connection cn = getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, title);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    out.add(new Object[]{
                            rs.getString(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getString(5),
                            rs.getString(6),
                            rs.getString(7)
                    });
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
        return out;
    }

    public List<Object[]> searchGamesBoughtBefore(int personId, Date toDate) throws DataAccessException {
        String sql = "select g.title, d.date, dl.quantity, dl.unitPrice " +
                "from game g " +
                "join documentLine dl on g.title = dl.game " +
                "join document d on dl.document = d.reference " +
                "join person p on d.person = p.id " +
                "where p.id = ? and d.date < ?";
        List<Object[]> out = new ArrayList<>();
        try (Connection cn = getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, personId);
            ps.setDate(2, new java.sql.Date(toDate.getTime()));
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    out.add(new Object[]{
                            rs.getString(1),
                            rs.getDate(2),
                            rs.getInt(3),
                            rs.getBigDecimal(4)
                    });
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
        return out;
    }

    public List<Object[]> searchGamesByAgeAndCountry(int ageLimit, String country) throws DataAccessException {
        String sql = "select p.name, p.firstName, p.birthDate, g.title, g.ageRestriction, ge.name " +
                "from person p " +
                "join document d on p.id = d.person " +
                "join documentLine dl on d.reference = dl.document " +
                "join game g on dl.game = g.title " +
                "join genre ge on g.genre = ge.name " +
                "join city c on p.zipCodeCity = c.zipCode and p.nameCity = c.name and p.country = c.country " +
                "where c.country = ? and g.ageRestriction >= ?";
        List<Object[]> out = new ArrayList<>();
        try (Connection cn = getConnection();
             PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, country);
            ps.setInt(2, ageLimit);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    out.add(new Object[]{
                            rs.getString(1),
                            rs.getString(2),
                            rs.getDate(3),
                            rs.getString(4),
                            rs.getInt(5),
                            rs.getString(6)
                    });
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
        return out;
    }

    public List<Object[]> getGames() throws DataAccessException {
        String sql = "select title from game";
        List<Object[]> out = new ArrayList<>();
        try (Connection cn = getConnection();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                out.add(new Object[]{rs.getString(1)});
            }
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
        return out;
    }

    public List<Object[]> getPersons() throws DataAccessException {
        String sql = "select id, name, firstName from person";
        List<Object[]> out = new ArrayList<>();
        try (Connection cn = getConnection();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                out.add(new Object[]{rs.getInt(1), rs.getString(2), rs.getString(3)});
            }
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
        return out;
    }

    public List<Object[]> getCities() throws DataAccessException {
        String sql = "select zipCode, name, country from city";
        List<Object[]> out = new ArrayList<>();
        try (Connection cn = getConnection();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                out.add(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3)});
            }
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
        return out;
    }

}