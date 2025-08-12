package dataAccess;

import exception.DataAccessException;
import model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDAO implements IPersonDAO {

    private final Connection conn;

    public PersonDAO() throws DataAccessException {
        try {
            this.conn = SingletonConnection.getInstance();
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public void insert(Person person) throws DataAccessException {
        String sql = "insert into person (name, firstName, phoneNumber, birthDate, boxNumber, accountNumber, streetName, streetNumber, isClient, isSupplier, zipCodeCity, nameCity, country) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, person.getName());
            ps.setObject(2, person.getFirstName(), Types.VARCHAR);
            ps.setString(3, person.getPhoneNumber());
            ps.setDate(4, new java.sql.Date(person.getBirthDate().getTime()));
            ps.setObject(5, person.getBoxNumber(), Types.INTEGER);
            ps.setObject(6, person.getAccountNumber(), Types.VARCHAR);
            ps.setString(7, person.getStreetName());
            ps.setInt(8, person.getStreetNumber());
            ps.setBoolean(9, person.isClient());
            ps.setBoolean(10, person.isSupplier());
            ps.setInt(11, person.getZipCodeCity());
            ps.setString(12, person.getNameCity());
            ps.setString(13, person.getCountry());
            if (ps.executeUpdate() == 0) {
                throw new DataAccessException("No rows affected");
            }
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public Person getPersonById(int id) throws DataAccessException {
        String sql = "select * from person where id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return map(rs);
                }
                return null;
            }
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public List<Person> getAllPerson() throws DataAccessException {
        List<Person> persons = new ArrayList<>();
        String sql = "select * from person order by name";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                persons.add(map(rs));
            }
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
        return persons;
    }

    @Override
    public void update(Person person) throws DataAccessException {
        String sql = "update person set name=?, firstName=?, phoneNumber=?, birthDate=?, boxNumber=?, accountNumber=?, streetName=?, streetNumber=?, isClient=?, isSupplier=?, zipCodeCity=?, nameCity=?, country=? where id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, person.getName());
            ps.setObject(2, person.getFirstName(), Types.VARCHAR);
            ps.setString(3, person.getPhoneNumber());
            ps.setDate(4, new java.sql.Date(person.getBirthDate().getTime()));
            ps.setObject(5, person.getBoxNumber(), Types.INTEGER);
            ps.setObject(6, person.getAccountNumber(), Types.VARCHAR);
            ps.setString(7, person.getStreetName());
            ps.setInt(8, person.getStreetNumber());
            ps.setBoolean(9, person.isClient());
            ps.setBoolean(10, person.isSupplier());
            ps.setInt(11, person.getZipCodeCity());
            ps.setString(12, person.getNameCity());
            ps.setString(13, person.getCountry());
            ps.setInt(14, person.getId());
            if (ps.executeUpdate() == 0) {
                throw new DataAccessException("No rows affected");
            }
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public void delete(int id) throws DataAccessException {
        String sql = "delete from person where id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            if (ps.executeUpdate() == 0) {
                throw new DataAccessException("No rows affected");
            }
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public boolean exists(Person person) throws DataAccessException {
        String sql = "SELECT COUNT(*) FROM person WHERE name = ? AND firstName = ? AND birthDate = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, person.getName());
            ps.setString(2, person.getFirstName());
            ps.setDate(3, new java.sql.Date(person.getBirthDate().getTime()));
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

    private Person map(ResultSet rs) throws DataAccessException {
        try {
            return new Person(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("firstName"),
                    rs.getString("phoneNumber"),
                    rs.getDate("birthDate"),
                    rs.getObject("boxNumber") != null ? rs.getInt("boxNumber") : null,
                    rs.getString("accountNumber"),
                    rs.getString("streetName"),
                    rs.getInt("streetNumber"),
                    rs.getBoolean("isClient"),
                    rs.getBoolean("isSupplier"),
                    rs.getInt("zipCodeCity"),
                    rs.getString("nameCity"),
                    rs.getString("country")
            );
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    @Override
    public boolean hasRelatedDocuments(int personId) throws DataAccessException {
        String sql = "SELECT COUNT(*) FROM document WHERE person = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, personId);
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
    public void deleteWithDocuments(int personId) throws DataAccessException {
        try {
            conn.setAutoCommit(false);

            try (PreparedStatement ps1 = conn.prepareStatement("DELETE FROM document WHERE person = ?")) {
                ps1.setInt(1, personId);
                ps1.executeUpdate();
            }

            try (PreparedStatement ps2 = conn.prepareStatement("DELETE FROM person WHERE id = ?")) {
                ps2.setInt(1, personId);
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

}