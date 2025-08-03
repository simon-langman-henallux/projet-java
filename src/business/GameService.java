package business;

import dataAccess.GameDAO;
import dataAccess.IGameDAO;
import exception.DataAccessException;
import model.Game;

import java.sql.SQLException;
import java.util.List;

public class GameService {

    private IGameDAO gameDAO;

    public GameService() {
        this.gameDAO = new GameDAO();
    }

    public void createGame(Game game) throws DataAccessException, SQLException {
        validateGame(game);
        if (gameDAO.getGameByTitle(game.getTitle()) != null) {
            throw new DataAccessException("A game with this title already exists.");
        }
        gameDAO.insert(game);
    }

    public void updateGame(Game game) throws DataAccessException, SQLException {
        validateGame(game);
        gameDAO.update(game);
    }

    public void deleteGame(String title) throws DataAccessException, SQLException {
        if (title == null || title.isBlank()) {
            throw new DataAccessException("Game title cannot be empty.");
        }
        gameDAO.delete(title);
    }

    public List<Game> getAllGames() throws DataAccessException, SQLException {
        return gameDAO.getAllGame();
    }

    public Game getGameByTitle(String title) throws DataAccessException, SQLException {
        return gameDAO.getGameByTitle(title);
    }

    private void validateGame(Game game) throws DataAccessException {
        if (game.getTitle() == null || game.getTitle().isBlank()) {
            throw new DataAccessException("Game title is required.");
        }
        if (game.getPrice() < 0) {
            throw new DataAccessException("Price must be positive.");
        }
        if (game.getReleaseDate() == null) {
            throw new DataAccessException("Release date is required.");
        }
        if (game.getAgeRestriction() < 0) {
            throw new DataAccessException("Age restriction must be positive.");
        }
        if (game.getPublisher() == null || game.getPublisher().isBlank()) {
            throw new DataAccessException("Publisher is required.");
        }
        if (game.getPlatform() == null || game.getPlatform().isBlank()) {
            throw new DataAccessException("Platform is required.");
        }
    }

}