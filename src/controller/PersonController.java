package controller;

import business.PersonService;
import exception.DataAccessException;
import model.Person;
import java.util.List;

public class PersonController {
    private final PersonService service = new PersonService();

    public List<Person> getAllPersons() throws DataAccessException {
        return service.getAllPerson();
    }

    public void createPerson(Person person) throws DataAccessException {
        service.create(person);
    }

    public void editPerson(Person person) throws DataAccessException {
        service.update(person);
    }

    public void removePerson(int id) throws DataAccessException {
        service.delete(id);
    }

    public PersonService getService() {
        return service;
    }

    public boolean hasRelatedDocuments(int personId) throws DataAccessException {
        return service.hasRelatedDocuments(personId);
    }

    public void deletePersonWithDocuments(int personId) throws DataAccessException {
        service.deleteWithDocuments(personId);
    }

}