package dataAccess;

import exception.DataAccessException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SearchDAO implements ISearchDAO {
    private final Connection conn;

    public SearchDAO() throws DataAccessException {
        try {
            conn = SingletonConnection.getInstance();
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public List<Object[]> searchByGameTitle(String title) throws DataAccessException {
        List<Object[]> results = new ArrayList<>();
        String sql = "select p.firstname, p.name, p.streetname, p.zipcodecity, p.namecity, c.name, co.currency from documentline dl join document d on d.reference = dl.document join person p on p.id = d.person join city c on c.zipcode = p.zipcodecity and c.name = p.namecity and c.country = p.country join country co on co.name = c.country where dl.game = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, title);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    results.add(new Object[]{
                            rs.getString(1), rs.getString(2), rs.getString(3),
                            rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7)
                    });
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
        return results;
    }

    @Override
    public List<Object[]> searchGamesBoughtBefore(int personId, Date toDate) throws DataAccessException {
        List<Object[]> results = new ArrayList<>();
        String sql = "select g.title, d.date, dl.quantity, dl.unitprice from document d join documentline dl on d.reference = dl.document join game g on g.title = dl.game where d.type = 'sale' and d.person = ? and d.date < ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, personId);
            ps.setDate(2, new java.sql.Date(toDate.getTime()));
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    results.add(new Object[]{
                            rs.getString(1), rs.getDate(2),
                            rs.getInt(3), rs.getDouble(4)
                    });
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
        return results;
    }

    @Override
    public List<Object[]> searchGamesByAgeAndCountry(int ageLimit, String country) throws DataAccessException {
        List<Object[]> results = new ArrayList<>();
        String sql = "select p.name, p.firstname, p.birthdate, g.title, g.agerestriction, g.genre from document d join documentline dl on d.reference = dl.document join game g on g.title = dl.game join person p on p.id = d.person where g.agerestriction >= ? and p.country = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, ageLimit);
            ps.setString(2, country);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    results.add(new Object[]{
                            rs.getString(1), rs.getString(2), rs.getDate(3),
                            rs.getString(4), rs.getInt(5), rs.getString(6)
                    });
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
        return results;
    }
}