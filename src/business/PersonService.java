package business;

import dataAccess.person.IPersonDAO;
import dataAccess.person.PersonDAO;
import exception.DataAccessException;
import exception.ValidationException;
import exception.DuplicateEntityException;
import exception.NotFoundException;
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
            throw new DuplicateEntityException("Person already exists");
        }
        personDAO.insert(person);
    }

    public Person getPersonById(int id) throws DataAccessException {
        if (id <= 0) {
            throw new ValidationException("Invalid ID");
        }
        Person p = personDAO.getPersonById(id);
        if (p == null) {
            throw new NotFoundException("Person not found");
        }
        return p;
    }

    public List<Person> getAllPerson() throws DataAccessException {
        return personDAO.getAllPerson();
    }

    public void update(Person person) throws DataAccessException {
        validate(person);
        if (person.getId() <= 0) {
            throw new ValidationException("Invalid ID");
        }
        if (personDAO.getPersonById(person.getId()) == null) {
            throw new NotFoundException("Person not found");
        }
        personDAO.update(person);
    }

    public void delete(int id) throws DataAccessException {
        if (id <= 0) {
            throw new ValidationException("Invalid ID");
        }
        if (personDAO.getPersonById(id) == null) {
            throw new NotFoundException("Person not found");
        }
        personDAO.delete(id);
    }

    public boolean hasRelatedDocuments(int personId) throws DataAccessException {
        if (personId <= 0) {
            throw new ValidationException("Invalid ID");
        }
        return personDAO.hasRelatedDocuments(personId);
    }

    public void deleteWithDocuments(int personId) throws DataAccessException {
        if (personId <= 0) {
            throw new ValidationException("Invalid ID");
        }
        if (personDAO.getPersonById(personId) == null) {
            throw new NotFoundException("Person not found");
        }
        personDAO.deleteWithDocuments(personId);
    }

    private void validate(Person person) {
        if (person == null) throw new ValidationException("Person is required");
        if (person.getName() == null || person.getName().isBlank()) throw new ValidationException("Last name is required");
        if (person.getPhoneNumber() == null || person.getPhoneNumber().isBlank()) throw new ValidationException("Phone number is required");
        if (person.getBirthDate() == null) throw new ValidationException("Birth date is required");
        if (person.getStreetName() == null || person.getStreetName().isBlank()) throw new ValidationException("Street name is required");
        if (person.getStreetNumber() <= 0) throw new ValidationException("Street number must be positive");
        if (!person.isClient() && !person.isSupplier()) throw new ValidationException("A person must be a client, a supplier, or both");
    }
}