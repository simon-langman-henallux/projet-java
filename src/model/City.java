package model;

public class City {

    private int zipCode;
    private String name;
    private String country;

    public City(int zipCode, String name, String country) {
        this.zipCode = zipCode;
        this.name = name;
        this.country = country;
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return name + " (" + zipCode + ")";
    }

}