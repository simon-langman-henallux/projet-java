package model;

public class City {

    private int zipCode;
    private String name;
    private Country country;

    public City(int zipCode, String name, Country country) {
        this.zipCode = zipCode;
        this.name = name;
        this.country = country;
    }

}