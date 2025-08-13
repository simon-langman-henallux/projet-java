package business;

import dataAccess.person.IPersonDAO;
import dataAccess.person.PersonDAO;
import exception.DataAccessException;
import model.Person;

import java.util.List;

public class PersonService {
    private final IPersonDAO personDAO;

    public PersonService() {
        this.personDAO = new PersonDAO();
    }

    public void create(Person person) throws DataAccessException {
        validate(person);
        if (personDAO.exists(person)) {
            throw new DataAccessException("Person already exists.");
        }
        personDAO.insert(person);
    }

    public Person getPersonById(int id) throws DataAccessException {
        return personDAO.getPersonById(id);
    }

    public List<Person> getAllPerson() throws DataAccessException {
        return personDAO.getAllPerson();
    }

    public void update(Person person) throws DataAccessException {
        validate(person);
        personDAO.update(person);
    }

    public void delete(int id) throws DataAccessException {
        personDAO.delete(id);
    }

    public boolean hasRelatedDocuments(int personId) throws DataAccessException {
        return personDAO.hasRelatedDocuments(personId);
    }

    public void deleteWithDocuments(int personId) throws DataAccessException {
        personDAO.deleteWithDocuments(personId);
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