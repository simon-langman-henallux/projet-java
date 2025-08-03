package dataAccess;

import exception.DataAccessException;
import model.Country;

import java.util.List;

public interface ICountryDAO {
    List<Country> getAllCountries() throws DataAccessException;
}