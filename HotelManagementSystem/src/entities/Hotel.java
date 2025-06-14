package entities;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Hotel {
    String hotelId;
    String name;
    String location;
    Map<String,Room> rooms=new HashMap<>();

    public Hotel(String hotelId, String name, String location) {
        this.hotelId = hotelId;
        this.name = name;
        this.location = location;
    }

    public void addRoom(Room room){
        rooms.put(room.roomId,room);
    }
    public Map<String,Room> getRooms(){
        return rooms;
    }

    public String getHotelId() {
        return hotelId;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }
}
