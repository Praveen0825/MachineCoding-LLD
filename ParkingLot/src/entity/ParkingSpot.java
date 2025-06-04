package entity;

public class ParkingSpot {
    private final int spotId;
    private final VehicleType vehicleType;
    private Vehicle parkedVehicle;

    public ParkingSpot(int spotId, VehicleType vehicleType) {
        this.spotId = spotId;
        this.vehicleType = vehicleType;
    }

    public int getSpotId() {
        return spotId;
    }


    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public Vehicle getParkedVehicle() {
        return parkedVehicle;
    }

    public boolean isAvailable(){
        return parkedVehicle==null;
    }

    public void parkVehicle(Vehicle vehicle) {
        if(parkedVehicle==null && vehicle.type== vehicleType) {
            parkedVehicle = vehicle;
        }
        else{
            throw new IllegalArgumentException("Invalid vehicle type or spot is not empty");
        }
    }
    public void unparkVehicle(){
        parkedVehicle=null;
    }
}
