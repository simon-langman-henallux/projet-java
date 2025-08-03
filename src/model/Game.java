package model;

import java.util.Date;

public class Game {

    private String title, description;
    private double price, duration;
    private Date releaseDate;
    private int ageRestriction, stock;
    private boolean isMultiplayer;
    private String publisher;
    private String genre;
    private String platform;

    public Game(String title, double price, Date releaseDate, String description, int ageRestriction, boolean isMultiplayer, double duration, int stock, String publisher, String genre, String platform) {
        this.title = title;
        this.price = price;
        this.releaseDate = releaseDate;
        this.description = description;
        this.ageRestriction = ageRestriction;
        this.isMultiplayer = isMultiplayer;
        this.duration = duration;
        this.stock = stock;
        this.publisher = publisher;
        this.genre = genre;
        this.platform = platform;
    }

    public Game() {}

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

    public int getStock() {
        return stock;
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

    public void setStock(int stock) {
        this.stock = stock;
    }

}