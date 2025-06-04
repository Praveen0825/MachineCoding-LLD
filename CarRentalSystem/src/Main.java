import entity.*;
import enums.*;
import service.*;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        CarService carService = new CarService();
        CustomerService customerService = new CustomerService();
        ReservationService reservationService = new ReservationService(carService);
        PaymentService paymentService = new PaymentService();

        Car car1 = new Car("1", "Toyota", "Camry", 2020, "ABC123", 50.0, CarType.SEDAN, true);
        Car car2 = new Car("2", "Honda", "Civic", 2019, "XYZ789", 45.0, CarType.SEDAN, true);
        carService.addCar(car1);
        carService.addCar(car2);

        Customer cust = new Customer("C1", "Alice", "9999999999", "alice@example.com", "DL123456");
        customerService.addCustomer(cust);

        Reservation res = reservationService.createReservation("1", "C1", LocalDate.now(), LocalDate.now().plusDays(3));
        if (res != null) {
            System.out.println("Reservation created: " + res.getId());
            double total = car1.getPricePerDay() * 3;
            Payment pay = paymentService.processPayment(res.getId(), total);
            System.out.println("Payment status: " + pay.getStatus());
        } else {
            System.out.println("Reservation failed.");
        }
    }
}