package controller;

import business.PersonService;
import exception.DataAccessException;
import model.Person;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class PersonController {
    private final PersonService service = new PersonService();

    public void loadPersons(JTable table) throws DataAccessException {
        List<Person> persons = service.getAllPerson();
        String[] columns = {"ID", "Name", "First Name", "Phone", "Birth Date", "Street", "Number", "Client", "Supplier"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        for (Person p : persons) {
            model.addRow(new Object[]{
                    p.getId(),
                    p.getName(),
                    p.getFirstName(),
                    p.getPhoneNumber(),
                    p.getBirthDate(),
                    p.getStreetName(),
                    p.getStreetNumber(),
                    p.isClient(),
                    p.isSupplier()
            });
        }

        table.setModel(model);
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