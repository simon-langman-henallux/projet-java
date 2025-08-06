package controller;

import business.GameService;
import exception.DataAccessException;
import model.Game;
import java.util.List;

public class GameController {

    private final GameService service = new GameService();

    public List<Game> getAllGames() throws DataAccessException {
        return service.getAllGames();
    }

    public void createGame(Game game) throws DataAccessException {
        service.create(game);
    }

    public void editGame(Game game, String originalTitle) throws DataAccessException {
        service.update(game, originalTitle);
    }

    public void removeGame(String title) throws DataAccessException {
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

}