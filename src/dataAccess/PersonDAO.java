package dataAccess;

import exception.DataAccessException;
import model.Person;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class PersonDAO implements IPersonDAO {

    Connection conn;

    public PersonDAO() throws DataAccessException {
        try {
            conn = dataAccess.SingletonConnection.getInstance();
        } catch (SQLException e) {
            throw new DataAccessException("Connections to Database Error");
        }
    }

    public void insert(Person person) throws SQLException {
        String sql = "insert into person (name, firstName, phoneNumber, birthDate, boxNumber, accountNumber, streetName, streetNumber, isClient, isSupplier, zipCodeCity, nameCity, country) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
            throw new DataAccessException("Insert Person Error");
        }
    }
    public Person find(int id) throws SQLException { return null; }
    public List<Person> findAll() throws SQLException { return null; }
    public void update(Person person) throws SQLException {}
    public void delete(int id) throws SQLException {}
}