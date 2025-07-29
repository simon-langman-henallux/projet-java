package Model;

public class City {

    private int ZipCode;
    private String name;
    private Country country;

    public City(int ZipCode, String name, Country country) {
        this.ZipCode = ZipCode;
        this.name = name;
        this.country = country;
    }

}