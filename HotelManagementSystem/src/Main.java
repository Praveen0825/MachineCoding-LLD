import entities.*;
import entities.Room;
import enums.*;
import service.HotelManagementService;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        HotelManagementService hms = new HotelManagementService();

        Hotel hotel = new Hotel("H1", "Sunrise Hotel", "Delhi");
        hms.addHotel(hotel);

        hms.addRoomToHotel("H1", new Room("101", RoomType.SINGLE, 1000));
        hms.addRoomToHotel("H1", new Room("102", RoomType.DOUBLE, 2000));

        Guest guest = new Guest("g1", "John Doe", "1234567890", "john@example.com");
        hms.addGuest(guest);

        String reservationId = hms.bookRoom("g1", "H1", RoomType.SINGLE, LocalDate.now(), LocalDate.now().plusDays(2));

        if (reservationId != null) {
            hms.checkIn(reservationId);
            hms.checkOut(reservationId, PaymentMethod.CREDIT_CARD);
            System.out.println("Reservation successful and completed.");
        } else {
            System.out.println("No rooms available.");
        }

        List<Room> searchResults = hms.searchAvailableRooms("H1", RoomType.SINGLE, 1500);
        System.out.println("Search results for SINGLE rooms under 1500: " + searchResults.size());

        List<Hotel> locationResults = hms.searchHotelsByLocationAndDate("Delhi", LocalDate.now(), LocalDate.now().plusDays(1));
        System.out.println("Hotels found in Delhi: " + locationResults.size());

        List<Room> detailedSearch = hms.searchRoomsBySizeLocationAndDate(RoomType.SINGLE, "Delhi", LocalDate.now(), LocalDate.now().plusDays(2));
        System.out.println("Rooms found by size, location, date: " + detailedSearch.size());
    }
}