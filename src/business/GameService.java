package business;

import dataAccess.SingletonConnection;
import dataAccess.game.GameDAO;
import dataAccess.game.IGameDAO;
import exception.DataAccessException;
import exception.ValidationException;
import exception.DuplicateEntityException;
import exception.NotFoundException;
import model.Game;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class GameService {

    private final IGameDAO gameDAO;

    public GameService() {
        try {
            Connection conn = SingletonConnection.getInstance();
            this.gameDAO = new GameDAO(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void create(Game game) throws DataAccessException {
        validate(game);
        if (gameDAO.getGameByTitle(game.getTitle()) != null) {
            throw new DuplicateEntityException("Game title already exists");
        }
        gameDAO.insert(game);
    }

    public void update(Game game, String originalTitle) throws DataAccessException {
        validate(game);
        if (originalTitle == null || originalTitle.isBlank()) {
            throw new ValidationException("Title is required");
        }
        if (gameDAO.getGameByTitle(originalTitle) == null) {
            throw new NotFoundException("Game not found");
        }
        if (!originalTitle.equals(game.getTitle()) && gameDAO.getGameByTitle(game.getTitle()) != null) {
            throw new DuplicateEntityException("Game title already exists");
        }
        gameDAO.update(game, originalTitle);
    }

    public void delete(String title) throws DataAccessException {
        if (title == null || title.isBlank()) {
            throw new ValidationException("Title is required");
        }
        if (gameDAO.getGameByTitle(title) == null) {
            throw new NotFoundException("Game not found");
        }
        gameDAO.delete(title);
    }

    public List<Game> getAllGames() throws DataAccessException {
        return gameDAO.getAllGame();
    }

    public Game getGameByTitle(String title) throws DataAccessException {
        if (title == null || title.isBlank()) {
            throw new ValidationException("Title is required");
        }
        Game g = gameDAO.getGameByTitle(title);
        if (g == null) {
            throw new NotFoundException("Game not found");
        }
        return g;
    }

    public boolean hasRelatedDocumentLines(String title) throws DataAccessException {
        if (title == null || title.isBlank()) {
            throw new ValidationException("Title is required");
        }
        return gameDAO.hasRelatedDocumentLines(title);
    }

    public void deleteWithDocumentLines(String title) throws DataAccessException {
        if (title == null || title.isBlank()) {
            throw new ValidationException("Title is required");
        }
        if (gameDAO.getGameByTitle(title) == null) {
            throw new NotFoundException("Game not found");
        }
        gameDAO.deleteWithDocumentLines(title);
    }

    private void validate(Game game) {
        if (game == null) throw new ValidationException("Game is required");
        if (game.getTitle() == null || game.getTitle().isBlank()) throw new ValidationException("Title is required");
        if (game.getPrice() < 0) throw new ValidationException("Price must be positive");
        if (game.getReleaseDate() == null) throw new ValidationException("Release date is required");
        if (game.getAgeRestriction() < 0) throw new ValidationException("Age restriction must be positive");
        if (game.getPublisher() == null || game.getPublisher().isBlank()) throw new ValidationException("Publisher is required");
        if (game.getPlatform() == null || game.getPlatform().isBlank()) throw new ValidationException("Platform is required");
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