package Model;

import java.util.Date;

public class Person {

    private int id, boxNumber, zipCodeCity;
    private String name, firstName, phoneNumber, accountNumber, streetName, nameCity, country;
    private Date birthDate;
    private boolean isClient, isSupplier;

    public Person(int id, String name, String firstName, String phoneNumber, Date birthDate, int boxNumber, String accountNumber, String streetName, boolean isClient, boolean isSupplier, int zipCodeCity, String nameCity, String country) {
        this.id = id;
        this.name = name;
        this.firstName = firstName;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.boxNumber = boxNumber;
        this.accountNumber = accountNumber;
        this.streetName = streetName;
        this.isClient = isClient;
        this.isSupplier = isSupplier;
        this.zipCodeCity = zipCodeCity;
        this.nameCity = nameCity;
        this.country = country;
    }

    public Person() {
        this(0, null, null, null, null, 0, null, null, false, false, 0, null, null);
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBoxNumber() {
        return boxNumber;
    }

    public void setBoxNumber(int boxNumber) {
        boxNumber = boxNumber;
    }

    public int getZipCodeCity() {
        return zipCodeCity;
    }

    public void setZipCodeCity(int zipCodeCity) {
        zipCodeCity = zipCodeCity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        firstName = firstName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        phoneNumber = phoneNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        accountNumber = accountNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        streetName = streetName;
    }

    public String getNameCity() {
        return nameCity;
    }

    public void setNameCity(String nameCity) {
        nameCity = nameCity;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        country = country;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        birthDate = birthDate;
    }

    public boolean isClient() {
        return isClient;
    }

    public void setClient(boolean client) {
        isClient = client;
    }

    public boolean isSupplier() {
        return isSupplier;
    }

    public void setSupplier(boolean supplier) {
        isSupplier = supplier;
    }
}