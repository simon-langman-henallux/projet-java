package controller;

import business.PersonService;
import exception.DataAccessException;
import model.Person;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
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
}