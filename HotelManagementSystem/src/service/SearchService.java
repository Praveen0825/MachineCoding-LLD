package service;

import entities.Hotel;
import entities.Room;
import enums.RoomStatus;
import enums.RoomType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class SearchService {
    public List<Room> searchRooms(Collection<Room> allRooms, RoomType roomType, double maxPrice) {
        List<Room> results = new ArrayList<>();
        for (Room room : allRooms) {
            if (room.getRoomType() == roomType && room.getPricePerNight() <= maxPrice && room.getStatus() == RoomStatus.AVAILABLE) {
                results.add(room);
            }
        }
        return results;
    }
    public List<Hotel> searchHotelsByLocationAndDate(Map<String, Hotel> allHotels, String location, LocalDate from, LocalDate to) {
        List<Hotel> results = new ArrayList<>();
        for (Hotel hotel : allHotels.values()) {
            if (!hotel.getLocation().equalsIgnoreCase(location)) continue;
            for (Room room : hotel.getRooms().values()) {
                if (room.getStatus() == RoomStatus.AVAILABLE) {
                    results.add(hotel);
                    break;
                }
            }
        }
        return results;
    }

    public List<Room> searchRoomsBySizeLocationAndDate(Map<String, Hotel> allHotels, RoomType roomType, String location, LocalDate checkInDate, LocalDate checkOutDate) {
        List<Room> matchingRooms = new ArrayList<>();
        for (Hotel hotel : allHotels.values()) {
            if (!hotel.getLocation().equalsIgnoreCase(location)) continue;
            for (Room room : hotel.getRooms().values()) {
                if (room.getRoomType() == roomType && room.getStatus() == RoomStatus.AVAILABLE) {
                    matchingRooms.add(room);
                }
            }
        }
        return matchingRooms;
    }

}
