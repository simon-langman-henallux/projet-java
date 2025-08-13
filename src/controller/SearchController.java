package controller;

import business.SearchService;
import exception.DataAccessException;
import java.util.Date;
import java.util.List;

public class SearchController {

    private final SearchService service;

    public SearchController() {
        this.service = new SearchService();
    }

    public String[] getCountries() throws DataAccessException {
        List<Object[]> results = service.getCountriesRaw();
        String[] countries = new String[results.size()];
        for (int i = 0; i < results.size(); i++) countries[i] = (String) results.get(i)[0];
        return countries;
    }

    public List<Object[]> searchGamesByAgeAndCountry(int ageLimit, String country) throws DataAccessException {
        return service.searchGamesByAgeAndCountry(ageLimit, country);
    }

    public List<Object[]> searchGamesBoughtBefore(int personId, Date toDate) throws DataAccessException {
        return service.searchGamesBoughtBefore(personId, toDate);
    }

    public List<Object[]> searchByGameTitle(String title) throws DataAccessException {
        return service.searchByGameTitle(title);
    }

    public List<Object[]> getGames() throws DataAccessException {
        return service.getGames();
    }

    public List<Object[]> getPersons() throws DataAccessException {
        return service.getPersons();
    }

    public List<Object[]> getCities() throws DataAccessException {
        return service.getCities();
    }
}