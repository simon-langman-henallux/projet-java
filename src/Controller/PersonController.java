package Controller;

import Business.PersonService;
import javax.swing.JTable;

public class PersonController {
    private PersonService service = new PersonService();
    public void loadPersons(JTable table) throws Exception {}
    public void createPerson() throws Exception {}
    public void editPerson(int id) throws Exception {}
    public void removePerson(int id) throws Exception {}
}