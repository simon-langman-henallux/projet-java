package Model;

public class ClientSearchResult {
    private String clientFirstName;
    private String clientName;
    private String clientStreetName;
    private int cityZipCode;
    private String countryName;
    private String countryCurrency;


    public ClientSearchResult(String clientFirstName, String clientName, String clientStreetName, int cityZipCode, String countryName, String countryCurrency) {
        this.clientFirstName = clientFirstName;
        this.clientName = clientName;
        this.clientStreetName = clientStreetName;
        this.cityZipCode = cityZipCode;
        this.countryName = countryName;
        this.countryCurrency = countryCurrency;
    }

    public String getClientFirstName() {
        return clientFirstName;
    }

    public String getClientName() {
        return clientName;
    }

    public String getClientStreetName() {
        return clientStreetName;
    }

    public int getCityZipCode() {
        return cityZipCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getCountryCurrency() {
        return countryCurrency;
    }
}
