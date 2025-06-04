package entity;

import java.util.ArrayList;
import java.util.List;

public class Level {
    private int floor;
    private List<ParkingSpot> spots;

    public Level(int floor, int totalSpots) {
        this.floor = floor;
        spots=new ArrayList<>(totalSpots);
        double spotsForBikes = 0.50;
        double spotsForCars = 0.40;

        int numBikes = (int) (totalSpots * spotsForBikes);
        int numCars = (int) (totalSpots * spotsForCars);

        for (int i = 1; i <= numBikes; i++) {
            spots.add(new ParkingSpot(i,VehicleType.BIKE));
        }
        for (int i = numBikes + 1; i <= numBikes + numCars; i++) {
            spots.add(new ParkingSpot(i,VehicleType.CAR));
        }
        for (int i = numBikes + numCars + 1; i <= totalSpots; i++) {
            spots.add(new ParkingSpot(i,VehicleType.TRUCK));
        }

    }

    public boolean parkVehicle(Vehicle vehicle){
        for(ParkingSpot spot:spots){
            if(spot.isAvailable()&&spot.getVehicleType()==vehicle.getType()){
                spot.parkVehicle(vehicle);
                return true;
            }
        }
        return false;
    }
    public boolean unparkVehicle(Vehicle vehicle){
        for(ParkingSpot spot:spots){
            if(spot.getParkedVehicle()==vehicle){
                spot.unparkVehicle();
                return true;
            }
        }
        return false;
    }

    public void displayAvailaleSpots(){
        System.out.println("Level "+floor+ " Availability: ");
        for (ParkingSpot spot:spots){
            System.out.println("Spot "+ spot.getSpotId() + ": " +(spot.isAvailable() ? "Available For"  : "Occupied By ")+" "+spot.getVehicleType());
        }
    }
}
