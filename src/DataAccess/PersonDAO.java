package DataAccess;

import Model.Person;

import java.sql.*;
import java.util.ArrayList;

public class PersonDAO {
    public ResultSet GameRestridedSearcbByCountryAndPegi(int idGame) {
        ResultSet resultSet = null;
        try {
            Connection connection = SingletonConnection.getInstance();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT p.name AS clientName, p.firstName AS clientFirstName, p.birthDate AS clientBirthDate, " +
                            "g.title AS gameTitle, g.ageRestriction AS gameAgeRestriction, ge.name AS genreName " +
                            "FROM person p " +
                            "INNER JOIN document d ON d.person = p.id " +
                            "INNER JOIN documentLine dl ON dl.document = d.reference " +
                            "INNER JOIN game g ON g.title = dl.game " +
                            "INNER JOIN genre ge ON ge.name = g.Genre " +
                            "WHERE p.Country = ? AND g.ageRestriction < ? AND p.isClient = 1 " +
                            "ORDER BY g.ageRestriction ASC"
            );

        }catch (SQLException e){
            e.printStackTrace();
        }
        return resultSet;
    }

    public static void insertPerson(Person person) throws SQLException {
        try (Connection conn = SingletonConnection.getInstance()) {
            String sql = "insert into person (name, firstName, phoneNumber, birthDate, boxNumber, accountNumber, streetName, isClient, isSupplier, zipCodeCity, nameCity, Country) " +
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
            String sql = "UPDATE person SET firstName=?, phoneNumber=?, birthDate=?, boxNumber=?, accountNumber=?, streetName=?, isClient=?, isSupplier=?, zipCodeCity=?, nameCity=?, Country=? WHERE name=?";
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
            String sql = "DELETE FROM person WHERE name=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.executeUpdate();
        }
    }

    public static ArrayList<Person> listPersons() throws SQLException {
        ArrayList<Person> persons = new ArrayList<>();
        try (Connection conn = SingletonConnection.getInstance()) {
            String sql = "SELECT * FROM person";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Person person = new Person();
                person.setId(rs.getInt("id"));
                person.setName(rs.getString("name"));
                person.setFirstName(rs.getString("firstName"));
                person.setPhoneNumber(rs.getString("phoneNumber"));
                person.setBirthDate(rs.getDate("birthDate"));
                person.setBoxNumber((Integer) rs.getObject("boxNumber"));
                person.setAccountNumber(rs.getString("accountNumber"));
                person.setStreetName(rs.getString("streetName"));
                person.setClient(rs.getBoolean("isClient"));
                person.setSupplier(rs.getBoolean("isSupplier"));
                person.setZipCodeCity(rs.getInt("zipCodeCity"));
                person.setNameCity(rs.getString("nameCity"));
                person.setCountry(rs.getString("Country"));
                persons.add(person);
            }
        }
        return persons;
    }

}
