package entity;

import enums.CarType;

public class Car {
    private String id;
    private String make;
    private String model;
    private int year;
    private String licensePlate;
    private double pricePerDay;
    private CarType type;
    private boolean isAvailable;

    public Car(String id, String make, String model, int year, String licensePlate, double pricePerDay, CarType type, boolean isAvailable) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;
        this.licensePlate = licensePlate;
        this.pricePerDay = pricePerDay;
        this.type = type;
        this.isAvailable = isAvailable;
    }

    public String getId() {
        return id;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public CarType getType() {
        return type;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
