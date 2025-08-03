package dataAccess;

import exception.DataAccessException;
import model.Country;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CountryDAO implements ICountryDAO {
    private final Connection conn;

    public CountryDAO() throws DataAccessException {
        try {
            conn = SingletonConnection.getInstance();
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public List<Country> getAllCountries() throws DataAccessException {
        List<Country> list = new ArrayList<>();
        String sql = "select name, currency from country order by name";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Country(
                        rs.getString("name"),
                        rs.getString("currency")
                ));
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error loading countries: " + e.getMessage());
        }
        return list;
    }

}