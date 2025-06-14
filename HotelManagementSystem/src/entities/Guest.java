package entities;

public class Guest {
    String guestId;
    String name;
    String contactNumber;
    String email;

    public Guest(String guestId, String name, String contactNumber, String email) {
        this.guestId = guestId;
        this.name = name;
        this.contactNumber = contactNumber;
        this.email = email;
    }

    public String getGuestId() {
        return guestId;
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
}
