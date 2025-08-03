package dataAccess;

import exception.DataAccessException;
import model.Game;
import java.sql.SQLException;
import java.util.List;

public interface IGameDAO {
    void insert(Game game) throws DataAccessException;
    void delete(String title) throws DataAccessException;
    void update(Game game) throws DataAccessException;
    Game getGameByTitle(String title) throws DataAccessException;
    List<Game> getAllGame() throws DataAccessException;
}