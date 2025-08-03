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

    public List<Object[]> searchByGame(String title, JTable table) throws DataAccessException {
        List<Object[]> rows = dao.searchByGameTitle(title);
        String[] columns = {"First Name", "Last Name", "Street", "Zip Code", "City", "Country", "Currency"};

        DefaultTableModel model = new DefaultTableModel(columns, 0);
        for (Object[] row : rows) {
            model.addRow(row);
        }
        table.setModel(model);
        return rows;
    }

    public List<Object[]> searchByDate(int personId, Date toDate) throws DataAccessException {
        return dao.searchGamesBoughtBefore(personId, toDate);
    }

    public List<Object[]> searchByAge(int age, String country) throws DataAccessException {
        return dao.searchGamesByAgeAndCountry(age, country);
    }

    public ISearchDAO getDao() {
        return dao;
    }
}