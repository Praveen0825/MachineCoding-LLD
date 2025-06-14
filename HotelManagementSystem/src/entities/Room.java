package entities;

import enums.RoomStatus;
import enums.RoomType;

public class Room {
    String roomId;
    RoomType roomType;
    RoomStatus roomStatus;
    double pricePerNight;

    public Room(String roomId, RoomType roomType, double pricePerNight) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
        this.roomStatus = RoomStatus.AVAILABLE;
    }

    public String getRoomId() {
        return roomId;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public RoomStatus getStatus() {
        return roomStatus;
    }

    public void setStatus(RoomStatus roomStatus) {
        this.roomStatus = roomStatus;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }
}
