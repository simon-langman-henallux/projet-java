package Model;

import java.sql.Date;

public class GamePegiSearchResult {
    private String clientName;
    private String clientFirstName;
    private Date clientBirthDate;
    private String gameTitle;
    private int gameAgeRestriction;
    private String genreName;

    public GamePegiSearchResult(String clientName, String clientFirstName, Date clientBirthDate, String gameTitle, int gameAgeRestriction, String genreName) {
        this.clientName = clientName;
        this.clientFirstName = clientFirstName;
        this.clientBirthDate = clientBirthDate;
        this.gameTitle = gameTitle;
        this.gameAgeRestriction = gameAgeRestriction;
        this.genreName = genreName;
    }

    public String getClientName() {
        return clientName;
    }

    public String getClientFirstName() {
        return clientFirstName;
    }

    public Date getClientBirthDate() {
        return clientBirthDate;
    }

    public String getGameTitle() {
        return gameTitle;
    }

    public int getGameAgeRestriction() {
        return gameAgeRestriction;
    }

    public String getGenreName() {
        return genreName;
    }
}
