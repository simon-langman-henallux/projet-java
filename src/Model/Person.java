package Model;

import java.util.Date;

public class Person {

    private int id, BoxNumber, ZipCodeCity;
    private String Name, FirstName, PhoneNumber, AccountNumber, StreetName, NameCity, Country;
    private Date BirthDate;
    private boolean IsClient, IsSupplier;

    public Person(int id, String Name, String FirstName, String PhoneNumber, Date BirthDate, int BoxNumber, String AccountNumber, String StreetName, boolean IsClient, boolean IsSupplier, int ZipCodeCity, String NameCity, String Country) {
        this.id = id;
        this.Name = Name;
        this.FirstName = FirstName;
        this.PhoneNumber = PhoneNumber;
        this.BirthDate = BirthDate;
        this.BoxNumber = BoxNumber;
        this.AccountNumber = AccountNumber;
        this.StreetName = StreetName;
        this.IsClient = IsClient;
        this.IsSupplier = IsSupplier;
        this.ZipCodeCity = ZipCodeCity;
        this.NameCity = NameCity;
        this.Country = Country;
    }

    public Person() {
        this(0, null, null, null, null, 0, null, null, false, false, 0, null, null);
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBoxNumber() {
        return BoxNumber;
    }

    public void setBoxNumber(int boxNumber) {
        BoxNumber = boxNumber;
    }

    public int getZipCodeCity() {
        return ZipCodeCity;
    }

    public void setZipCodeCity(int zipCodeCity) {
        ZipCodeCity = zipCodeCity;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getAccountNumber() {
        return AccountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        AccountNumber = accountNumber;
    }

    public String getStreetName() {
        return StreetName;
    }

    public void setStreetName(String streetName) {
        StreetName = streetName;
    }

    public String getNameCity() {
        return NameCity;
    }

    public void setNameCity(String nameCity) {
        NameCity = nameCity;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public Date getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(Date birthDate) {
        BirthDate = birthDate;
    }

    public boolean isClient() {
        return IsClient;
    }

    public void setClient(boolean client) {
        IsClient = client;
    }

    public boolean isSupplier() {
        return IsSupplier;
    }

    public void setSupplier(boolean supplier) {
        IsSupplier = supplier;
    }
}
