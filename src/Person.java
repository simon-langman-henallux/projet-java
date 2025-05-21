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

}
