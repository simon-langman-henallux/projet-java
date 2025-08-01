package DataAccess;

import Model.Person;
import java.sql.SQLException;
import java.util.List;

public class PersonDAO implements IPersonDAO {
    public void create(Person person) throws SQLException {}
    public Person find(int id) throws SQLException { return null; }
    public List<Person> findAll() throws SQLException { return null; }
    public void update(Person person) throws SQLException {}
    public void delete(int id) throws SQLException {}
}