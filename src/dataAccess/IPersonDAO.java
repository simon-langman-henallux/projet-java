package dataAccess;

import model.Person;
import java.sql.SQLException;
import java.util.List;

public interface IPersonDAO {
    void insert(Person person) throws SQLException;
    Person findById(int id) throws SQLException;
    List<Person> findAll() throws SQLException;
    void update(Person person) throws SQLException;
    void delete(int id) throws SQLException;
}