package entity;

import enums.CarType;
import enums.PaymentStatus;

import java.time.LocalDate;

public class Payment {
    private String id;
    private String reservationId;
    private double amount;
    private LocalDate paymentDate;
    private PaymentStatus status;

    public Payment(String id, String reservationId, double amount, LocalDate paymentDate, PaymentStatus status) {
        this.id = id;
        this.reservationId = reservationId;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getReservationId() {
        return reservationId;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public PaymentStatus getStatus() {
        return status;
    }
}
