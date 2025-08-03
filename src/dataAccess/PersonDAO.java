package dataAccess;

import exception.DataAccessException;
import model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDAO implements IPersonDAO {

    Connection conn;

    public PersonDAO() throws DataAccessException {
        try {
            conn = dataAccess.SingletonConnection.getInstance();
        } catch (SQLException e) {
            throw new DataAccessException("personDAO Connections to Database Error");
        }
    }
    @Override
    public void insert(Person person) {
        String sql = "insert into person (name, firstName, phoneNumber, birthDate, boxNumber, accountNumber, streetName, streetNumber, isClient, isSupplier, zipCodeCity, nameCity, country) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, person.getName());
            ps.setObject(2, person.getFirstName(), Types.VARCHAR);
            ps.setString(3, person.getPhoneNumber());
            ps.setDate(4, new java.sql.Date(person.getBirthDate().getTime())); //java.sql.Date convertit un format de date quelconque en format sql java
            ps.setObject(5, person.getBoxNumber(), Types.INTEGER);
            ps.setObject(6, person.getAccountNumber(), Types.VARCHAR);
            ps.setString(7, person.getStreetName());
            ps.setInt(8, person.getStreetNumber());
            ps.setBoolean(9, person.isClient());
            ps.setBoolean(10, person.isSupplier());
            ps.setInt(11, person.getZipCodeCity());
            ps.setString(12, person.getNameCity());
            ps.setString(13, person.getCountry());
            int affected = ps.executeUpdate();
            if (affected == 0) {
                throw new DataAccessException("No rows affected");
            }
        } catch (SQLException e) {
            throw new DataAccessException("Insert Person Error");
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
                } else return null;
            }
        } catch  (SQLException e) {
            throw new DataAccessException("Get Person by id Error");
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
            throw new DataAccessException("findAll Error");
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
            ps.setDate(4, new java.sql.Date(person.getBirthDate().getTime())); //java.sql.Date convertit un format de date quelconque en format sql java
            ps.setObject(5, person.getBoxNumber(), Types.INTEGER);
            ps.setObject(6, person.getAccountNumber(), Types.VARCHAR);
            ps.setString(7, person.getStreetName());
            ps.setInt(8, person.getStreetNumber());
            ps.setBoolean(9, person.isClient());
            ps.setBoolean(10, person.isSupplier());
            ps.setInt(11, person.getZipCodeCity());
            ps.setString(12, person.getNameCity());
            ps.setString(13, person.getCountry());
            int affected = ps.executeUpdate();
            if (affected == 0) {
                throw new DataAccessException("No rows affected");
            }
        } catch (SQLException e) {
            throw new DataAccessException("Update Person Error");
        }
    }
    @Override
    public void delete(int id) throws DataAccessException {
        String sql = "delete from person where id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int affected = ps.executeUpdate();
            if (affected == 0) {
                throw new DataAccessException("No rows affected");
            }
        } catch (SQLException e) {
            throw new DataAccessException("Delete Person Error");
        }
    }
    private Person map(ResultSet rs) throws SQLException {
        return new Person(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("firstName"),
                rs.getString("phoneNumber"),
                rs.getDate("birthDate"),
                rs.getInt("boxNumber"),
                rs.getString("accountNumber"),
                rs.getString("streetName"),
                rs.getInt("streetNumber"),
                rs.getBoolean("isClient"),
                rs.getBoolean("isSupplier"),
                rs.getInt("zipCodeCity"),
                rs.getString("nameCity"),
                rs.getString("country")
        );
    }
}