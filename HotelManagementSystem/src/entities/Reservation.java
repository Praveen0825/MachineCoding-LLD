package entities;

import java.time.LocalDate;

public class Reservation {
    String reservationId;
    String guestId;
    String hotelId;
    String roomId;
    LocalDate checkInDate;
    LocalDate checkOutDate;
    boolean isCheckedIn=false;
    boolean isCheckedOut=false;
    Payment payment;

    public Reservation(String reservationId, String guestId, String hotelId, String roomId, LocalDate checkInDate, LocalDate checkOutDate) {
        this.reservationId = reservationId;
        this.guestId = guestId;
        this.hotelId = hotelId;
        this.roomId = roomId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getGuestId() {
        return guestId;
    }

    public String getHotelId() {
        return hotelId;
    }

    public String getRoomId() {
        return roomId;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public boolean isCheckedIn() {
        return isCheckedIn;
    }

    public boolean isCheckedOut() {
        return isCheckedOut;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setCheckedIn(boolean checkedIn) {
        isCheckedIn = checkedIn;
    }

    public void setCheckedOut(boolean checkedOut) {
        isCheckedOut = checkedOut;
    }

    public void setPayment(Payment payment) {
        payment=payment;
    }
}
