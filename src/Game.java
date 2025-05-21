import java.util.Date;

public class Game {

    private String title, Description;
    private double Price, Duration;
    private Date ReleaseDate;
    private int AgeRestriction;
    private boolean IsMultiplayer;
    private Publisher Publisher;
    private Genre Genre;
    private Platform Platform;

    public Game(String title, String Description, double Price, double Duration, Date ReleaseDate, int AgeRestriction, boolean IsMultiplayer, Publisher Publisher, Genre Genre, Platform Platform) {
        this.title = title;
        this.Description = Description;
        this.Price = Price;
        this.Duration = Duration;
        this.ReleaseDate = ReleaseDate;
        this.AgeRestriction = AgeRestriction;
        this.IsMultiplayer = IsMultiplayer;
        this.Publisher = Publisher;
        this.Genre = Genre;
        this.Platform = Platform;
    }

}
