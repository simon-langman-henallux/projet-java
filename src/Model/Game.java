package Model;

import java.util.Date;

public class Game {

    private String title, description;
    private double price, duration;
    private Date date;
    private int ageRestriction;
    private boolean isMultiplayer;
    private Publisher publisher;
    private Genre genre;
    private Platform platform;

    public Game(String title, String description, double price, double duration, Date date, int ageRestriction, boolean isMultiplayer, Publisher publisher, Genre genre, Platform platform) {
        setTitle(title);
        setDescription(description);
        setPrice(price);
        setDuration(duration);
        setReleaseDate(date);
        setAgeRestriction(ageRestriction);
        setMultiplayer(isMultiplayer);
        setPublisher(publisher);
        setGenre(genre);
        setPlatform(platform);
    }
    public Game(){
        this(null, null, 0.0, 0.0, null, 0, false, null,null, null);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        description = description;
    }

    public void setPrice(double price) {
        price = price;
    }

    public void setDuration(double duration) {
        duration = duration;
    }

    public void setReleaseDate(Date date) {
        date = date;
    }

    public void setAgeRestriction(int ageRestriction) {
        ageRestriction = ageRestriction;
    }

    public void setMultiplayer(boolean multiplayer) {
        isMultiplayer = multiplayer;
    }

    public void setPublisher(Publisher publisher) {
        publisher = publisher;
    }

    public void setGenre(Genre genre) {
        genre = genre;
    }

    public void setPlatform(Platform platform) {
        platform = platform;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public double getDuration() {
        return duration;
    }

    public Date getReleaseDate() {
        return date;
    }

    public int getAgeRestriction() {
        return ageRestriction;
    }

    public boolean isMultiplayer() {
        return isMultiplayer;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public Genre getGenre() {
        return genre;
    }

    public Platform getPlatform() {
        return platform;
    }
}