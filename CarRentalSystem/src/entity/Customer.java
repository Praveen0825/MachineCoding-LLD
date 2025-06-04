package entity;

public class Customer {
    private String id;
    private String name;
    private String contactNumber;
    private String email;
    private String driverLicense;

    public Customer(String id, String name, String contactNumber, String email, String driverLicense) {
        this.id = id;
        this.name = name;
        this.contactNumber = contactNumber;
        this.email = email;
        this.driverLicense = driverLicense;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getDriverLicense() {
        return driverLicense;
    }
}
