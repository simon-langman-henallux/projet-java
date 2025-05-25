package Model;

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
        setTitle(title);
        setDescription(Description);
        setPrice(Price);
        setDuration(Duration);
        setReleaseDate(ReleaseDate);
        setAgeRestriction(AgeRestriction);
        setMultiplayer(IsMultiplayer);
        setPublisher(Publisher);
        setGenre(Genre);
        setPlatform(Platform);
    }
    public Game(){
        this(null, null, 0.0, 0.0, null, 0, false, null,null, null);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public void setDuration(double duration) {
        Duration = duration;
    }

    public void setReleaseDate(Date releaseDate) {
        ReleaseDate = releaseDate;
    }

    public void setAgeRestriction(int ageRestriction) {
        AgeRestriction = ageRestriction;
    }

    public void setMultiplayer(boolean multiplayer) {
        IsMultiplayer = multiplayer;
    }

    public void setPublisher(Model.Publisher publisher) {
        Publisher = publisher;
    }

    public void setGenre(Model.Genre genre) {
        Genre = genre;
    }

    public void setPlatform(Model.Platform platform) {
        Platform = platform;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return Description;
    }

    public double getPrice() {
        return Price;
    }

    public double getDuration() {
        return Duration;
    }

    public Date getReleaseDate() {
        return ReleaseDate;
    }

    public int getAgeRestriction() {
        return AgeRestriction;
    }

    public boolean isMultiplayer() {
        return IsMultiplayer;
    }

    public Publisher getPublisher() {
        return Publisher;
    }

    public Genre getGenre() {
        return Genre;
    }

    public Platform getPlatform() {
        return Platform;
    }
}
