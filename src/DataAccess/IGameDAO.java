package DataAccess;

import Model.Game;
import java.sql.SQLException;
import java.util.List;

public interface IGameDAO {
    void create(Game game) throws SQLException;
    Game find(String title) throws SQLException;
    List<Game> findAll() throws SQLException;
    void update(Game game) throws SQLException;
    void delete(String title) throws SQLException;
}