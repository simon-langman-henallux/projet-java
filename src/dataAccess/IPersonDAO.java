package dataAccess;

import exception.DataAccessException;
import model.Person;
import java.util.List;

public interface IPersonDAO {
    void insert(Person person) throws DataAccessException;
    Person getPersonById(int id) throws DataAccessException;
    List<Person> getAllPerson() throws DataAccessException;
    void update(Person person) throws DataAccessException;
    void delete(int id) throws DataAccessException;
}