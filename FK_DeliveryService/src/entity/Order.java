package entity;

import enums.OrderStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Order {
    private String name;
    private int pincode;
    private OrderStatus status;
    private LocalDateTime scheduledTime;
    private int durationMinutes;

    public Order(String name, int pincode) {
        this.name = name;
        this.pincode = pincode;
        this.status = OrderStatus.CREATED;
    }

    public Order(String name, int pincode, LocalDateTime scheduledTime, int durationMinutes) {
        this(name, pincode);
        this.scheduledTime = scheduledTime;
        this.durationMinutes = durationMinutes;
    }

    public String getName() {
        return name;
    }

    public int getPincode() {
        return pincode;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public LocalDateTime getScheduledTime() {
        return scheduledTime;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public boolean isScheduled() {
        return scheduledTime != null;
    }

}
