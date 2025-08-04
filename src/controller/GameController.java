package controller;

import business.GameService;
import exception.DataAccessException;
import model.Game;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class GameController {
    private final GameService service = new GameService();

    public void loadGames(JTable table) throws DataAccessException {
        List<Game> games = service.getAllGames();
        String[] columns = {"Title", "Price", "Release Date", "Age", "Multiplayer", "Stock"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        for (Game g : games) {
            model.addRow(new Object[]{
                    g.getTitle(),
                    g.getPrice(),
                    g.getReleaseDate(),
                    g.getAgeRestriction(),
                    g.isMultiplayer(),
                    g.getStock()
            });
        }

        table.setModel(model);
    }

    public void createGame(Game game) throws DataAccessException {
        service.create(game);
    }

    public void editGame(Game game) throws DataAccessException {
        service.update(game);
    }

    public void removeGame(String title) throws DataAccessException {
        service.delete(title);
    }

    public GameService getService() {
        return service;
    }

    public List<String> getPublisherNames() throws DataAccessException {
        return service.getAllPublisherNames();
    }
    public List<String> getPlatformNames() throws DataAccessException {
        return service.getAllPlatformNames();
    }
    public List<String> getGenreNames() throws DataAccessException {
        return service.getAllGenreNames();
    }

}