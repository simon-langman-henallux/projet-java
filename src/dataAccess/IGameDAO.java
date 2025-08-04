package dataAccess;

import exception.DataAccessException;
import model.Game;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface IGameDAO {
    void insert(Game game) throws DataAccessException;
    void delete(String title) throws DataAccessException;
    void update(Game game) throws DataAccessException;
    Game getGameByTitle(String title) throws DataAccessException;
    List<Game> getAllGame() throws DataAccessException;
    Map<String, BigDecimal> getTotalSalesByGenre() throws DataAccessException;
    Map<String, BigDecimal> getAveragePriceByPublisher() throws DataAccessException;
    List<String> getAllPublisherNames() throws DataAccessException;
    List<String> getAllPlatformNames() throws DataAccessException;
    List<String> getAllGenreNames() throws DataAccessException;
}