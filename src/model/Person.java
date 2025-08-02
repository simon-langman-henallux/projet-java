package model;

import java.util.Date;

public class Person {

    private int id, boxNumber, streetNumber, zipCodeCity;
    private String name, firstName, phoneNumber, accountNumber, streetName, nameCity, country;
    private Date birthDate;
    private boolean isClient, isSupplier;
    // constructeur utile pour les delete, update etc...
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
    // constructeur pour les insert car id auto-incrémenter
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

    public void setId(int id) {
        this.id = id;
    }

    public void setBoxNumber(int boxNumber) {
        this.boxNumber = boxNumber;
    }

    public void setZipCodeCity(int zipCodeCity) {
        this.zipCodeCity = zipCodeCity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public void setNameCity(String nameCity) {
        this.nameCity = nameCity;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setClient(boolean client) {
        isClient = client;
    }

    public void setSupplier(boolean supplier) {
        isSupplier = supplier;
    }
}