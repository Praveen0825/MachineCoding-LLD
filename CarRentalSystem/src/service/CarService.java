package service;

import entity.Car;
import enums.CarType;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class CarService {
    private Map<String, Car> cars=new ConcurrentHashMap<>();
    public void addCar(Car car){
        cars.put(car.getId(),car);
    }

    public List<Car> searchCars(CarType type, double minPrice, double maxPrice, boolean onlyAvailable){
        List<Car> carList=cars.values().stream()
                .filter(c-> (type==null)||(type==c.getType())&& c.getPricePerDay()>=minPrice&&c.getPricePerDay()<=maxPrice
                &&(!onlyAvailable || c.isAvailable())).toList();
        return carList;
    }

    public void updateAvailability(String carId, boolean available){
        if(cars.containsKey(carId)){
            cars.get(carId).setAvailable(available);
        }
    }

    public Car getCar(String carId){
        return cars.get(carId);
    }
}
