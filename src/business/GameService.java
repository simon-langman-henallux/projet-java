package business;

import dataAccess.GameDAO;
import dataAccess.IGameDAO;
import exception.DataAccessException;
import model.Game;

import java.util.List;

public class GameService {

    private final IGameDAO gameDAO;

    public GameService() {
        this.gameDAO = new GameDAO();
    }

    public void create(Game game) throws DataAccessException {
        validate(game);
        if (gameDAO.getGameByTitle(game.getTitle()) != null) {
            throw new DataAccessException("A game with this title already exists.");
        }
        gameDAO.insert(game);
    }

    public void update(Game game, String originalTitle) throws DataAccessException {
        validate(game);
        gameDAO.update(game, originalTitle);
    }

    public void delete(String title) throws DataAccessException {
        if (title == null || title.isBlank()) {
            throw new DataAccessException("Game title cannot be empty.");
        }
        gameDAO.delete(title);
    }

    public List<Game> getAllGames() throws DataAccessException {
        return gameDAO.getAllGame();
    }

    public Game getGameByTitle(String title) throws DataAccessException {
        return gameDAO.getGameByTitle(title);
    }

    public boolean hasRelatedDocumentLines(String title) throws DataAccessException {
        return gameDAO.hasRelatedDocumentLines(title);
    }

    public void deleteWithDocumentLines(String title) throws DataAccessException {
        gameDAO.deleteWithDocumentLines(title);
    }

    private void validate(Game game) throws DataAccessException {
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

    public List<String> getAllPublisherNames() throws DataAccessException {
        return gameDAO.getAllPublisherNames();
    }

    public List<String> getAllPlatformNames() throws DataAccessException {
        return gameDAO.getAllPlatformNames();
    }

    public List<String> getAllGenreNames() throws DataAccessException {
        return gameDAO.getAllGenreNames();
    }

}