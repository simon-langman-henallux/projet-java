package DataAccess;

import Model.Person;
import java.sql.*;
import java.util.ArrayList;

public class PersonCRUD {

    public static void insertPerson(Person person) throws SQLException {
        try (Connection conn = SingletonConnection.getInstance()) {
            String sql = "INSERT INTO PERSON (Name, FirstName, PhoneNumber, BirthDate, BoxNumber, AccountNumber, StreetName, IsClient, IsSupplier, ZipCodeCity, NameCity, Country) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, person.getName());
            ps.setString(2, person.getFirstName());
            ps.setString(3, person.getPhoneNumber());
            ps.setDate(4, new java.sql.Date(person.getBirthDate().getTime()));
            ps.setObject(5, person.getBoxNumber(), Types.INTEGER);
            ps.setString(6, person.getAccountNumber());
            ps.setString(7, person.getStreetName());
            ps.setBoolean(8, person.isClient());
            ps.setBoolean(9, person.isSupplier());
            ps.setInt(10, person.getZipCodeCity());
            ps.setString(11, person.getNameCity());
            ps.setString(12, person.getCountry());
            ps.executeUpdate();
        }
    }

    public static void updatePerson(Person person) throws SQLException {
        try (Connection conn = SingletonConnection.getInstance()) {
            String sql = "UPDATE PERSON SET FirstName=?, PhoneNumber=?, BirthDate=?, BoxNumber=?, AccountNumber=?, StreetName=?, IsClient=?, IsSupplier=?, ZipCodeCity=?, NameCity=?, Country=? WHERE Name=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, person.getFirstName());
            ps.setString(2, person.getPhoneNumber());
            ps.setDate(3, new java.sql.Date(person.getBirthDate().getTime()));
            ps.setObject(4, person.getBoxNumber(), Types.INTEGER);
            ps.setString(5, person.getAccountNumber());
            ps.setString(6, person.getStreetName());
            ps.setBoolean(7, person.isClient());
            ps.setBoolean(8, person.isSupplier());
            ps.setInt(9, person.getZipCodeCity());
            ps.setString(10, person.getNameCity());
            ps.setString(11, person.getCountry());
            ps.setString(12, person.getName());
            ps.executeUpdate();
        }
    }

    public static void deletePerson(String name) throws SQLException {
        try (Connection conn = SingletonConnection.getInstance()) {
            String sql = "DELETE FROM PERSON WHERE Name=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.executeUpdate();
        }
    }

    public static ArrayList<Person> listPersons() throws SQLException {
        ArrayList<Person> persons = new ArrayList<>();
        try (Connection conn = SingletonConnection.getInstance()) {
            String sql = "SELECT * FROM PERSON";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Person person = new Person();
                person.setId(rs.getInt("id"));
                person.setName(rs.getString("Name"));
                person.setFirstName(rs.getString("FirstName"));
                person.setPhoneNumber(rs.getString("PhoneNumber"));
                person.setBirthDate(rs.getDate("BirthDate"));
                person.setBoxNumber((Integer) rs.getObject("BoxNumber"));
                person.setAccountNumber(rs.getString("AccountNumber"));
                person.setStreetName(rs.getString("StreetName"));
                person.setClient(rs.getBoolean("IsClient"));
                person.setSupplier(rs.getBoolean("IsSupplier"));
                person.setZipCodeCity(rs.getInt("ZipCodeCity"));
                person.setNameCity(rs.getString("NameCity"));
                person.setCountry(rs.getString("Country"));
                persons.add(person);
            }
        }
        return persons;
    }
}