package controller;

import dataAccess.ISearchDAO;
import dataAccess.SearchDAO;
import exception.DataAccessException;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.Date;
import java.util.List;

public class SearchController {
    private final ISearchDAO dao = new SearchDAO();

    public void searchByGame(String title, JTable table) throws DataAccessException {
        List<Object[]> rows = dao.searchByGameTitle(title);
        String[] columns = {"First Name", "Last Name", "Street", "Zip Code", "City", "Country", "Currency"};

        DefaultTableModel model = new DefaultTableModel(columns, 0);
        for (Object[] row : rows) {
            model.addRow(row);
        }
        table.setModel(model);
    }

    public void searchByDate(int personId, Date toDate, JTable table) throws DataAccessException {
        List<Object[]> rows = dao.searchGamesBoughtBefore(personId, toDate);
        String[] columns = {"Game Title", "Document Date", "Quantity", "Unit Price"};

        DefaultTableModel model = new DefaultTableModel(columns, 0);
        for (Object[] row : rows) {
            model.addRow(row);
        }
        table.setModel(model);
    }

    public void searchByAge(int ageLimit, String country, JTable table) throws DataAccessException {
        List<Object[]> rows = dao.searchGamesByAgeAndCountry(ageLimit, country);
        String[] columns = {"Name", "First Name", "Birth Date", "Game Title", "Age Restriction", "Genre"};

        DefaultTableModel model = new DefaultTableModel(columns, 0);
        for (Object[] row : rows) {
            model.addRow(row);
        }
        table.setModel(model);
    }

    public ISearchDAO getDao() {
        return dao;
    }
}