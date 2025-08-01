package DataAccess;

import Model.Person;
import java.sql.SQLException;
import java.util.List;

public interface IPersonDAO {
    void create(Person person) throws SQLException;
    Person find(int id) throws SQLException;
    List<Person> findAll() throws SQLException;
    void update(Person person) throws SQLException;
    void delete(int id) throws SQLException;
}