package service;

import interfaces.Rentable;
import interfaces.Searchable;
import model.*;

import java.util.*;
import java.time.LocalDate;

public class CarRentalService implements Rentable, Searchable {
    private List<Car> cars = new ArrayList<>();
    private List<Rental> rentals = new ArrayList<>();

    public void loadCars(List<Car> carList) { this.cars = carList; }

    public List<Car> getCars() { return cars; }

    public boolean addCar(Car car) {
        return cars.add(car);
    }

    public boolean removeCar(int id) {
        Car car = searchById(id);
        if (car != null) {
            car.setAvailable(false);
            return true;
        }
        return false;
    }

    @Override
    public boolean rentCar(int carId, Customer customer, LocalDate start, LocalDate end) {
        Car car = searchById(carId);
        if (car != null && car.isAvailable()) {
            car.setAvailable(false);
            rentals.add(new Rental(car, customer, start, end));
            return true;
        }
        return false;
    }

    @Override
    public boolean returnCar(int carId) {
        Car car = searchById(carId);
        if (car != null && !car.isAvailable()) {
            car.setAvailable(true);
            return true;
        }
        return false;
    }

    @Override
    public List<Car> searchByModel(String model) {
        List<Car> result = new ArrayList<>();
        for (Car car : cars) {
            if (car.getModel().equalsIgnoreCase(model)) result.add(car);
        }
        return result;
    }

    @Override
    public Car searchById(int id) {
        for (Car car : cars) {
            if (car.getId() == id) return car;
        }
        return null;
    }

    @Override
    public List<Car> searchByStatus(boolean isAvailable) {
        List<Car> result = new ArrayList<>();
        for (Car car : cars) {
            if (car.isAvailable() == isAvailable) result.add(car);
        }
        return result;
    }
}
