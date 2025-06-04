package entity;

import enums.ReservationStatus;

import java.time.LocalDate;

public class Reservation {
    private String id;
    private String carId;
    private String customerId;
    private LocalDate startDate;
    private LocalDate endDate;
    private ReservationStatus status;

    public Reservation(String id, String carId, String customerId, LocalDate startDate, LocalDate endDate, ReservationStatus status) {
        this.id = id;
        this.carId = carId;
        this.customerId = customerId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getCarId() {
        return carId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }
}
