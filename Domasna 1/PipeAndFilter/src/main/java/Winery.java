public class Winery {
    private String name;
    private String address;
    private String city;
    private String phone;
    private String website;
    private String email;

    // Constructors, getters, and setters

    public Winery(String name, String address, String city, String phone, String website, String email) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.phone = phone;
        this.website = website;
        this.email = email;
    }

    // Getters and setters for each field

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Additional methods if needed

    @Override
    public String toString() {
        return "Winery{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", phone='" + phone + '\'' +
                ", website='" + website + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}