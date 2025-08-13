package model;

import java.util.Date;
import java.util.Objects;

public class Person {

    private int id;
    private String name;
    private String firstName;
    private String phoneNumber;
    private Date birthDate;
    private Integer boxNumber;
    private String accountNumber;
    private String streetName;
    private int streetNumber;
    private boolean isClient;
    private boolean isSupplier;
    private int zipCodeCity;
    private String nameCity;
    private String country;

    public Person() {}

    public Person(int id,
                  String name,
                  String firstName,
                  String phoneNumber,
                  Date birthDate,
                  Integer boxNumber,
                  String accountNumber,
                  String streetName,
                  int streetNumber,
                  boolean isClient,
                  boolean isSupplier,
                  int zipCodeCity,
                  String nameCity,
                  String country) {
        setId(id);
        setName(name);
        setFirstName(firstName);
        setPhoneNumber(phoneNumber);
        setBirthDate(birthDate);
        setBoxNumber(boxNumber);
        setAccountNumber(accountNumber);
        setStreetName(streetName);
        setStreetNumber(streetNumber);
        setClient(isClient);
        setSupplier(isSupplier);
        setZipCodeCity(zipCodeCity);
        setNameCity(nameCity);
        setCountry(country);
    }

    public int getId() { return id; }
    public void setId(int id) {
        if (id < 0) throw new IllegalArgumentException("id < 0");
        this.id = id;
    }

    public String getName() { return name; }
    public void setName(String name) {
        String v = Objects.requireNonNull(name, "name").trim();
        if (v.isEmpty()) throw new IllegalArgumentException("name empty");
        if (v.length() > 20) throw new IllegalArgumentException("name too long");
        this.name = v;
    }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) {
        String v = Objects.requireNonNull(firstName, "firstName").trim();
        if (v.isEmpty()) throw new IllegalArgumentException("firstName empty");
        if (v.length() > 20) throw new IllegalArgumentException("firstName too long");
        this.firstName = v;
    }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) {
        String v = Objects.requireNonNull(phoneNumber, "phoneNumber").trim();
        if (v.isEmpty()) throw new IllegalArgumentException("phoneNumber empty");
        if (v.length() > 15) throw new IllegalArgumentException("phoneNumber too long");
        if (!v.matches("^[+0-9\\- ]{6,15}$")) throw new IllegalArgumentException("invalid phoneNumber");
        this.phoneNumber = v;
    }

    public Date getBirthDate() { return birthDate == null ? null : new Date(birthDate.getTime()); }
    public void setBirthDate(Date birthDate) {
        Date v = Objects.requireNonNull(birthDate, "birthDate");
        this.birthDate = new Date(v.getTime());
    }

    public Integer getBoxNumber() { return boxNumber; }
    public void setBoxNumber(Integer boxNumber) {
        if (boxNumber != null && boxNumber < 0) throw new IllegalArgumentException("boxNumber < 0");
        this.boxNumber = boxNumber;
    }

    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) {
        String v = null;
        if (accountNumber != null && !accountNumber.isBlank()) {
            v = accountNumber.trim();
            if (v.length() > 30) throw new IllegalArgumentException("accountNumber too long");
        }
        this.accountNumber = v;
    }

    public String getStreetName() { return streetName; }
    public void setStreetName(String streetName) {
        String v = Objects.requireNonNull(streetName, "streetName").trim();
        if (v.isEmpty()) throw new IllegalArgumentException("streetName empty");
        if (v.length() > 100) throw new IllegalArgumentException("streetName too long");
        this.streetName = v;
    }

    public int getStreetNumber() { return streetNumber; }
    public void setStreetNumber(int streetNumber) {
        if (streetNumber <= 0) throw new IllegalArgumentException("streetNumber <= 0");
        this.streetNumber = streetNumber;
    }

    public boolean isClient() { return isClient; }
    public void setClient(boolean client) { isClient = client; }

    public boolean isSupplier() { return isSupplier; }
    public void setSupplier(boolean supplier) { isSupplier = supplier; }

    public int getZipCodeCity() { return zipCodeCity; }
    public void setZipCodeCity(int zipCodeCity) { this.zipCodeCity = zipCodeCity; }

    public String getNameCity() { return nameCity; }
    public void setNameCity(String nameCity) {
        String v = Objects.requireNonNull(nameCity, "nameCity").trim();
        if (v.isEmpty()) throw new IllegalArgumentException("nameCity empty");
        if (v.length() > 20) throw new IllegalArgumentException("nameCity too long");
        this.nameCity = v;
    }

    public String getCountry() { return country; }
    public void setCountry(String country) {
        String v = Objects.requireNonNull(country, "country").trim();
        if (v.isEmpty()) throw new IllegalArgumentException("country empty");
        if (v.length() > 30) throw new IllegalArgumentException("country too long");
        this.country = v;
    }

    @Override
    public String toString() {
        return id + " - " + name + " (Client: " + isClient + ", Supplier: " + isSupplier + ")";
    }

}