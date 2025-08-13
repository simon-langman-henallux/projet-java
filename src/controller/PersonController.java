package controller;

import business.PersonService;
import exception.DataAccessException;
import exception.ValidationException;
import exception.DuplicateEntityException;
import exception.NotFoundException;
import model.Person;
import java.util.List;

public class PersonController {
    private final PersonService service = new PersonService();

    public List<Person> getAllPersons() throws DataAccessException {
        return service.getAllPerson();
    }

    public void createPerson(Person person) throws DataAccessException, ValidationException, DuplicateEntityException {
        service.create(person);
    }

    public void editPerson(Person person) throws DataAccessException, ValidationException, NotFoundException {
        service.update(person);
    }

    public void removePerson(int id) throws DataAccessException, ValidationException, NotFoundException {
        service.delete(id);
    }

    public PersonService getService() {
        return service;
    }

    public boolean hasRelatedDocuments(int personId) throws DataAccessException, ValidationException {
        return service.hasRelatedDocuments(personId);
    }

    public void deletePersonWithDocuments(int personId) throws DataAccessException, ValidationException, NotFoundException {
        service.deleteWithDocuments(personId);
    }
}