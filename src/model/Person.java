package model;

import java.util.Date;

public class Person {

    private int id, boxNumber, streetNumber, zipCodeCity;
    private String name, firstName, phoneNumber, accountNumber, streetName, nameCity, country;
    private Date birthDate;
    private boolean isClient, isSupplier;

    public Person(int id, String name, String firstName, String phoneNumber, Date birthDate, int boxNumber, String accountNumber, String streetName, int streetNumber, boolean isClient, boolean isSupplier, int zipCodeCity, String nameCity, String country) {
        this.id = id;
        this.name = name;
        this.firstName = firstName;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.boxNumber = boxNumber;
        this.accountNumber = accountNumber;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.isClient = isClient;
        this.isSupplier = isSupplier;
        this.zipCodeCity = zipCodeCity;
        this.nameCity = nameCity;
        this.country = country;
    }

    public Person(String name, String firstName, String phoneNumber, Date birthDate, int boxNumber, String accountNumber, String streetName, int streetNumber, boolean isClient, boolean isSupplier, int zipCodeCity, String nameCity, String country) {
        this.name = name;
        this.firstName = firstName;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.boxNumber = boxNumber;
        this.accountNumber = accountNumber;
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.isClient = isClient;
        this.isSupplier = isSupplier;
        this.zipCodeCity = zipCodeCity;
        this.nameCity = nameCity;
        this.country = country;
    }

    public Person() {
        this(null, null, null, null, 0, null, null, 0, false, false, 0, null, null);
    }

    public int getId() {
        return id;
    }

    public int getBoxNumber() {
        return boxNumber;
    }

    public int getZipCodeCity() {
        return zipCodeCity;
    }

    public String getName() {
        return name;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public String getNameCity() {
        return nameCity;
    }

    public String getCountry() {
        return country;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public boolean isClient() {
        return isClient;
    }

    public boolean isSupplier() {
        return isSupplier;
    }

}