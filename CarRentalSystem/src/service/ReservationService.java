package service;

import entity.Car;
import entity.Reservation;
import enums.ReservationStatus;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class ReservationService {
    private Map<String, Reservation> reservations=new ConcurrentHashMap<>();
    private CarService carService;

    public ReservationService(CarService carService) {
        this.carService = carService;
    }

    public synchronized Reservation createReservation(String carId, String customerId, LocalDate start, LocalDate end){
        Car car=carService.getCar(carId);
        if(car==null|| !car.isAvailable())
            return null;
        String id= UUID.randomUUID().toString();
        Reservation reservation=new Reservation(id,carId, customerId, start, end, ReservationStatus.BOOKED);
        reservations.put(id,reservation);
        carService.updateAvailability(carId,false);

        return reservation;
    }

    public boolean cancelReservation(String reservationId){
        Reservation reservation=reservations.get(reservationId);
        if(reservation==null||reservation.getStatus()==ReservationStatus.CANCELLED)
            return false;
        reservation.setStatus(ReservationStatus.CANCELLED);
        carService.updateAvailability(reservation.getCarId(),true);
        return true;
    }
    public Reservation getReservation(String id) {
        return reservations.get(id);
    }
}
