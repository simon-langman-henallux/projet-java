package dataAccess;

import exception.DataAccessException;
import model.Person;

import java.sql.*;
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

    public void insert(Person person) {
        String sql = """
        INSERT INTO person
          (name, firstName, phoneNumber, birthDate, boxNumber,
           accountNumber, streetName, streetNumber,
           isClient, isSupplier, zipCodeCity, nameCity, country)
        VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)
        """;
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

            int affected = ps.executeUpdate();
            if (affected == 0) {
                throw new DataAccessException("Échec de l’insertion de Person");
            }
        } catch (SQLException e) {
            throw new DataAccessException("Insert Person Error");
        }
    }

    public Person find(int id) throws SQLException { return null; }
    public List<Person> findAll() throws SQLException { return null; }
    public void update(Person person) throws SQLException {}
    public void delete(int id) throws SQLException {}
}