package DataAccess;

import Model.Game;
import java.sql.SQLException;
import java.util.List;

public class GameDAO implements IGameDAO {
    public void create(Game game) throws SQLException {}
    public Game find(String title) throws SQLException { return null; }
    public List<Game> findAll() throws SQLException { return null; }
    public void update(Game game) throws SQLException {}
    public void delete(String title) throws SQLException {}
}