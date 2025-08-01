package controller;

import business.GameService;
import javax.swing.JTable;

public class GameController {
    private GameService service = new GameService();
    public void loadGames(JTable table) throws Exception {}
    public void createGame() throws Exception {}
    public void editGame(String title) throws Exception {}
    public void removeGame(String title) throws Exception {}
}