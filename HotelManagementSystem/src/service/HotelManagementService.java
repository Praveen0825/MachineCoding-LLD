package service;

import entities.*;
import enums.*;
import enums.RoomType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class HotelManagementService {
    Map<String, Guest> guests=new HashMap<>();
    Map<String, Hotel> hotels=new HashMap<>();
    Map<String, Reservation> reservations=new HashMap<>();
    SearchService searchService=new SearchService();

    public void addGuest(Guest guest){
        guests.put(guest.getGuestId(),guest);
    }

    public void addHotel(Hotel hotel){
        if(hotel!=null) {
            hotels.put(hotel.getHotelId(), hotel);
        }
    }

    public void addRoomToHotel(String hotelId, Room room){
        Hotel hotel=hotels.get(hotelId);
        if(hotel!=null){
            hotel.addRoom(room);
        }
        else{
            System.out.println("Hotel not found");
        }
    }

    public synchronized String bookRoom(String guestId, String hotelId, RoomType roomType, LocalDate from, LocalDate to) {
        Hotel hotel = hotels.get(hotelId);
        if (hotel == null) return null;

        for (Room room : hotel.getRooms().values()) {
            if (room.getRoomType() == roomType && room.getStatus() == RoomStatus.AVAILABLE) {
                room.setStatus(RoomStatus.RESERVED);
                String reservationId = UUID.randomUUID().toString();
                Reservation reservation = new Reservation(reservationId, guestId, hotelId, room.getRoomId(), from, to);
                reservations.put(reservationId, reservation);
                return reservationId;
            }
        }
        return null;
    }

    public synchronized boolean checkIn(String reservationId) {
        Reservation reservation = reservations.get(reservationId);
        if (reservation != null && !reservation.isCheckedIn()) {
            Hotel hotel = hotels.get(reservation.getHotelId());
            Room room = hotel.getRooms().get(reservation.getRoomId());
            room.setStatus(RoomStatus.OCCUPIED);
            reservation.setCheckedIn(true);
            return true;
        }
        return false;
    }

    public synchronized boolean checkOut(String reservationId, PaymentMethod method) {
        Reservation reservation = reservations.get(reservationId);
        if (reservation != null && reservation.isCheckedIn() && !reservation.isCheckedOut()) {
            Hotel hotel = hotels.get(reservation.getHotelId());
            Room room = hotel.getRooms().get(reservation.getRoomId());
            room.setStatus(RoomStatus.AVAILABLE);
            reservation.setCheckedOut(true);

            long days = reservation.getCheckOutDate().toEpochDay() - reservation.getCheckInDate().toEpochDay();
            double totalAmount = days * room.getPricePerNight();
            Payment payment = new Payment(UUID.randomUUID().toString(), totalAmount, method, LocalDateTime.now(), true);
            reservation.setPayment(payment);
            return true;
        }
        return false;
    }

    public List<Room> getAvailableRooms(String hotelId, RoomType roomType) {
        Hotel hotel = hotels.get(hotelId);
        List<Room> available = new ArrayList<>();
        if (hotel != null) {
            for (Room room : hotel.getRooms().values()) {
                if (room.getRoomType() == roomType && room.getStatus() == RoomStatus.AVAILABLE) {
                    available.add(room);
                }
            }
        }
        return available;
    }

    public List<Room> searchAvailableRooms(String hotelId, RoomType type, double maxPrice) {
        Hotel hotel = hotels.get(hotelId);
        if (hotel != null) {
            return searchService.searchRooms(hotel.getRooms().values(), type, maxPrice);
        }
        return Collections.emptyList();
    }

    public List<Hotel> searchHotelsByLocationAndDate(String location, LocalDate from, LocalDate to) {
        return searchService.searchHotelsByLocationAndDate(hotels, location, from, to);
    }

    public List<Room> searchRoomsBySizeLocationAndDate(RoomType type, String location, LocalDate from, LocalDate to) {
        return searchService.searchRoomsBySizeLocationAndDate(hotels, type, location, from, to);
    }

}
