package service;

import entity.Payment;
import enums.PaymentStatus;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PaymentService {
    private final Map<String, Payment> payments = new HashMap<>();

    public Payment processPayment(String reservationId, double amount) {
        String id = UUID.randomUUID().toString();
        Payment payment = new Payment(id, reservationId, amount, LocalDate.now(), PaymentStatus.COMPLETED);
        payments.put(id, payment);
        return payment;
    }

}
