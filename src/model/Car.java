package model;

public class Car extends Vehicle {
    private boolean isAvailable;

    public Car(int id, String make, String model, int year, String type, boolean isAvailable) {
        super(id, make, model, year, type);
        this.isAvailable = isAvailable;
    }

    @Override
    public String getDetails() {
        return id + ", " + make + ", " + model + ", " + year + ", " + type + ", " + (isAvailable ? "Available" : "Rented");
    }

    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }
}
