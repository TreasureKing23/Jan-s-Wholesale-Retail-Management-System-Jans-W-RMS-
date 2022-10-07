package format;

public class Address {
    private String street;
    private String city;
    private String parish;


    public Address(String street, String city, String parish) {
        this.street = street;
        this.city = city;

    }

    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public String getParish() {
        return parish;
    }
    public void setParish(String parish) {
        this.parish = parish;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", parish='" + parish + '\'' +
                '}';
    }
}
