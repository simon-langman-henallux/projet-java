package model;

import java.util.Date;

public class Game {

    private String title, description;
    private double price, duration;
    private Date releaseDate;
    private int ageRestriction;
    private boolean isMultiplayer;
    private String publisher;
    private String genre;
    private String platform;

    public Game(String title, double price, Date resleaseDate, String description, int ageRestriction, boolean isMultiplayer, double duration, String publisher, String genre, String platform) {
        this.title = title;
        this.price = price;
        this.releaseDate = resleaseDate;
        this.description = description;
        this.ageRestriction = ageRestriction;
        this.isMultiplayer = isMultiplayer;
        this.duration = duration;
        this.publisher = publisher;
        this.genre = genre;
        this.platform = platform;
    }
    public Game(){
        this(null, 0.0, null, null, 0, false, 0, null,null, null);
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
        return releaseDate;
    }

    public int getAgeRestriction() {
        return ageRestriction;
    }

    public boolean isMultiplayer() {
        return isMultiplayer;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getGenre() {
        return genre;
    }

    public String getPlatform() {
        return platform;
    }
}