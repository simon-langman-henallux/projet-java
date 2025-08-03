package model;

public class Country {

    private String name, currency;

    public Country(String name, String currency) {
        this.name = name;
        this.currency = currency;
    }

    public String getName() {
        return name;
    }

    public String getCurrency() {
        return currency;
    }

    @Override
    public String toString() {
        return name;
    }

}