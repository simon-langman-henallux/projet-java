package controller;

import dataAccess.ISearchDAO;
import dataAccess.SearchDAO;
import exception.DataAccessException;
import model.City;

import java.util.Date;
import java.util.List;

public class SearchController {

    private final ISearchDAO dao = new SearchDAO();

    public String[] getCountries() throws DataAccessException {
        List<Object[]> results = dao.getCountries();
        String[] countries = new String[results.size()];
        for (int i = 0; i < results.size(); i++) {
            countries[i] = (String) results.get(i)[0];
        }
        return countries;
    }

    public List<Object[]> searchGamesByAgeAndCountry(int ageLimit, String country) throws DataAccessException {
        return dao.searchGamesByAgeAndCountry(ageLimit, country);
    }

    public List<Object[]> searchGamesBoughtBefore(int personId, Date toDate) throws DataAccessException {
        return dao.searchGamesBoughtBefore(personId, toDate);
    }

    public List<Object[]> searchByGameTitle(String title) throws DataAccessException {
        return dao.searchByGameTitle(title);
    }

    public List<Object[]> getGames() throws DataAccessException {
        return dao.getGames();
    }

    public List<Object[]> getPersons() throws DataAccessException {
        return dao.getPersons();
    }

    public List<Object[]> getCities() throws DataAccessException {
        return dao.getCities();
    }

}