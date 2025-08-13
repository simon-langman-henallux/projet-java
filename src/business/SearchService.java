package business;

import java.sql.Connection;
import dataAccess.SingletonConnection;
import dataAccess.game.GameDAO;
import dataAccess.game.IGameDAO;
import dataAccess.search.ISearchDAO;
import dataAccess.search.SearchDAO;
import exception.DataAccessException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class SearchService {

    private final ISearchDAO searchdao;

    public SearchService() {
        try {
            Connection conn = SingletonConnection.getInstance();
            this.searchdao = new SearchDAO(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Object[]> searchByGameTitle(String title) throws DataAccessException {
        return searchdao.searchByGameTitle(title);
    }

    public List<Object[]> searchGamesBoughtBefore(int personId, Date toDate) throws DataAccessException {
        return searchdao.searchGamesBoughtBefore(personId, toDate);
    }

    public List<Object[]> searchGamesByAgeAndCountry(int ageLimit, String country) throws DataAccessException {
        return searchdao.searchGamesByAgeAndCountry(ageLimit, country);
    }

    public List<Object[]> getGames() throws DataAccessException {
        return searchdao.getGames();
    }

    public List<Object[]> getPersons() throws DataAccessException {
        return searchdao.getPersons();
    }

    public List<Object[]> getCities() throws DataAccessException {
        return searchdao.getCities();
    }

    public List<Object[]> getCountriesRaw() throws DataAccessException {
        return searchdao.getCountries();
    }
}