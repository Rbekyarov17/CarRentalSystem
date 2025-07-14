package io;

import model.Car;

import java.io.*;
import java.util.List;

public class CarFileWriter {
    private String fileName;

    public CarFileWriter(String fileName) {
        this.fileName = fileName;
    }

    public void writeCars(List<Car> cars) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Car car : cars) {
                bw.write(car.getId() + "," + car.getMake() + "," + car.getModel() + "," +
                        car.getYear() + "," + car.getType() + "," +
                        (car.isAvailable() ? "Available" : "Rented"));
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
