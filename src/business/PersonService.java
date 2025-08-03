package business;

import dataAccess.IPersonDAO;
import dataAccess.PersonDAO;
import exception.DataAccessException;
import model.Person;

import java.sql.SQLException;
import java.util.List;

public class PersonService {
    private final IPersonDAO personDAO;

    public PersonService() {
        this.personDAO = new PersonDAO();
    }

    public void createPerson(Person person) throws DataAccessException, SQLException {
        validate(person);
        personDAO.insert(person);
    }

    public Person getPersonById(int id) throws DataAccessException, SQLException {
        return personDAO.getPersonById(id);
    }

    public List<Person> getAllPerson() throws DataAccessException, SQLException {
        return personDAO.getAllPerson();
    }

    public void update(Person person) throws DataAccessException, SQLException {
        validate(person);
        personDAO.update(person);
    }

    public void delete(int id) throws DataAccessException, SQLException {
        personDAO.delete(id);
    }

    private void validate(Person person) throws DataAccessException {
        if (person.getName() == null || person.getName().isBlank()) {
            throw new DataAccessException("Last name is required.");
        }
        if (person.getPhoneNumber() == null || person.getPhoneNumber().isBlank()) {
            throw new DataAccessException("Phone number is required.");
        }
        if (person.getBirthDate() == null) {
            throw new DataAccessException("Birth date is required.");
        }
        if (person.getStreetName() == null || person.getStreetName().isBlank()) {
            throw new DataAccessException("Street name is required.");
        }
        if (person.getStreetNumber() <= 0) {
            throw new DataAccessException("Street number must be positive.");
        }
        if (!person.isClient() && !person.isSupplier()) {
            throw new DataAccessException("A person must be a client, a supplier, or both.");
        }
    }

}