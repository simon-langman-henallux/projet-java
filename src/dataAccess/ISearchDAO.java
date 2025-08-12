package dataAccess;

import exception.DataAccessException;
import java.util.Date;
import java.util.List;

public interface ISearchDAO {
    List<Object[]> searchByGameTitle(String title) throws DataAccessException;
    List<Object[]> searchGamesBoughtBefore(int personId, Date toDate) throws DataAccessException;
    List<Object[]> searchGamesByAgeAndCountry(int ageLimit, String country) throws DataAccessException;
    List<Object[]> getCountries() throws DataAccessException;
    List<Object[]> getGames() throws DataAccessException;
    List<Object[]> getPersons() throws DataAccessException;
    List<Object[]> getCities() throws DataAccessException;
}