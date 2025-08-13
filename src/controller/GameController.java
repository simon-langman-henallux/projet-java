package controller;

import business.GameService;
import exception.DataAccessException;
import exception.ValidationException;
import exception.DuplicateEntityException;
import exception.NotFoundException;
import model.Game;

import java.util.List;

public class GameController {

    private final GameService service;

    public GameController() {
        this.service = new GameService();
    }

    public List<Game> getAllGames() throws DataAccessException {
        return service.getAllGames();
    }

    public void createGame(Game game) throws DataAccessException, ValidationException, DuplicateEntityException {
        service.create(game);
    }

    public void editGame(Game game, String originalTitle) throws DataAccessException, ValidationException, NotFoundException, DuplicateEntityException {
        service.update(game, originalTitle);
    }

    public void removeGame(String title) throws DataAccessException, ValidationException, NotFoundException {
        service.delete(title);
    }

    public GameService getService() {
        return service;
    }

    public List<String> getAllPublisherNames() throws DataAccessException {
        return service.getAllPublisherNames();
    }

    public List<String> getPlatformNames() throws DataAccessException {
        return service.getAllPlatformNames();
    }

    public List<String> getGenreNames() throws DataAccessException {
        return service.getAllGenreNames();
    }

    public boolean hasRelatedDocumentLines(String title) throws DataAccessException, ValidationException {
        return service.hasRelatedDocumentLines(title);
    }

    public void deleteWithDocumentLines(String title) throws DataAccessException, ValidationException, NotFoundException {
        service.deleteWithDocumentLines(title);
    }
}