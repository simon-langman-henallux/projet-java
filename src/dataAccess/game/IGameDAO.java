package dataAccess.game;

import exception.DataAccessException;
import model.Game;
import java.util.List;

public interface IGameDAO {
    void insert(Game game) throws DataAccessException;
    void delete(String title) throws DataAccessException;
    void update(Game game, String originalTitle) throws DataAccessException;
    Game getGameByTitle(String title) throws DataAccessException;
    List<Game> getAllGame() throws DataAccessException;
    List<String> getAllPublisherNames() throws DataAccessException;
    List<String> getAllPlatformNames() throws DataAccessException;
    List<String> getAllGenreNames() throws DataAccessException;
    boolean hasRelatedDocumentLines(String title) throws DataAccessException;
    void deleteWithDocumentLines(String title) throws DataAccessException;
}