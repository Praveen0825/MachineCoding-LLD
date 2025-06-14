package entities;

import enums.PaymentMethod;

import java.time.LocalDateTime;

public class Payment {
    String paymentId;
    double amount;
    PaymentMethod method;
    LocalDateTime time;
    boolean isSuccessful;

    public Payment(String paymentId, double amount, PaymentMethod method, LocalDateTime time, boolean isSuccessful) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.method = method;
        this.time = time;
        this.isSuccessful = isSuccessful;
    }
}
