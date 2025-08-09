package model;

import java.util.Date;
import java.util.Objects;

public class Game {
    private String title;
    private double price;
    private Date releaseDate;
    private String description;
    private int ageRestriction;
    private boolean multiplayer;
    private double duration;
    private int stock;
    private String publisher;
    private String genre;
    private String platform;

    public Game(String title,
                double price,
                Date releaseDate,
                String description,
                int ageRestriction,
                boolean multiplayer,
                double duration,
                int stock,
                String publisher,
                String genre,
                String platform) {
        setTitle(title);
        setPrice(price);
        setReleaseDate(releaseDate);
        setDescription(description);
        setAgeRestriction(ageRestriction);
        setMultiplayer(multiplayer);
        setDuration(duration);
        setStock(stock);
        setPublisher(publisher);
        setGenre(genre);
        setPlatform(platform);
    }

    public String getTitle() { return title; }
    public void setTitle(String title) {
        String v = Objects.requireNonNull(title, "title").trim();
        if (v.isEmpty()) throw new IllegalArgumentException("title empty");
        if (v.length() > 200) throw new IllegalArgumentException("title too long");
        this.title = v;
    }

    public double getPrice() { return price; }
    public void setPrice(double price) {
        if (Double.isNaN(price) || Double.isInfinite(price)) throw new IllegalArgumentException("price invalid");
        if (price < 0) throw new IllegalArgumentException("price < 0");
        if (price > 1_000_000) throw new IllegalArgumentException("price too high");
        this.price = price;
    }

    public Date getReleaseDate() {
        return releaseDate == null ? null : new Date(releaseDate.getTime());
    }
    public void setReleaseDate(Date releaseDate) {
        Date v = Objects.requireNonNull(releaseDate, "releaseDate");
        this.releaseDate = new Date(v.getTime());
    }

    public String getDescription() { return description; }
    public void setDescription(String description) {
        String v = null;
        if (description != null && !description.isBlank()) {
            v = description.trim();
            if (v.length() > 2000) throw new IllegalArgumentException("description too long");
        }
        this.description = v;
    }

    public int getAgeRestriction() { return ageRestriction; }
    public void setAgeRestriction(int ageRestriction) {
        if (ageRestriction < 0) throw new IllegalArgumentException("ageRestriction < 0");
        if (ageRestriction > 21) throw new IllegalArgumentException("ageRestriction too high");
        this.ageRestriction = ageRestriction;
    }

    public boolean isMultiplayer() { return multiplayer; }
    public void setMultiplayer(boolean multiplayer) { this.multiplayer = multiplayer; }

    public double getDuration() { return duration; }
    public void setDuration(double duration) {
        if (Double.isNaN(duration) || Double.isInfinite(duration)) throw new IllegalArgumentException("duration invalid");
        if (duration < 0) throw new IllegalArgumentException("duration < 0");
        if (duration > 100_000) throw new IllegalArgumentException("duration too high");
        this.duration = duration;
    }

    public int getStock() { return stock; }
    public void setStock(int stock) {
        if (stock < 0) throw new IllegalArgumentException("stock < 0");
        if (stock > 10_000_000) throw new IllegalArgumentException("stock too high");
        this.stock = stock;
    }

    public String getPublisher() { return publisher; }
    public void setPublisher(String publisher) {
        String v = Objects.requireNonNull(publisher, "publisher").trim();
        if (v.isEmpty()) throw new IllegalArgumentException("publisher empty");
        if (v.length() > 200) throw new IllegalArgumentException("publisher too long");
        this.publisher = v;
    }

    public String getGenre() { return genre; }
    public void setGenre(String genre) {
        String v = Objects.requireNonNull(genre, "genre").trim();
        if (v.isEmpty()) throw new IllegalArgumentException("genre empty");
        if (v.length() > 100) throw new IllegalArgumentException("genre too long");
        this.genre = v;
    }

    public String getPlatform() { return platform; }
    public void setPlatform(String platform) {
        String v = Objects.requireNonNull(platform, "platform").trim();
        if (v.isEmpty()) throw new IllegalArgumentException("platform empty");
        if (v.length() > 100) throw new IllegalArgumentException("platform too long");
        this.platform = v;
    }
}