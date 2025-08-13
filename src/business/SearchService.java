package business;

import dataAccess.game.IGameDAO;
import dataAccess.game.GameDAO;

public class SearchService {

    private final IGameDAO gamedao;

    public SearchService() {
        this.gamedao = new GameDAO();
    }

}