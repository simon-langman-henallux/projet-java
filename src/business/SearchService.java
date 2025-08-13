package business;

import dataAccess.IGameDAO;
import dataAccess.GameDAO;

public class SearchService {

    private final IGameDAO gamedao;

    public SearchService() {
        this.gamedao = new GameDAO();
    }

}