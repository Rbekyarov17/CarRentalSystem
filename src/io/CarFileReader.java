package io;

import model.Car;

import java.io.*;
import java.util.*;

public class CarFileReader {
    private String fileName;

    public CarFileReader(String fileName) {
        this.fileName = fileName;
    }

    public List<Car> readCars() {
        List<Car> cars = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                if (tokens.length == 6) {
                    int id = Integer.parseInt(tokens[0].trim());
                    String make = tokens[1].trim();
                    String model = tokens[2].trim();
                    int year = Integer.parseInt(tokens[3].trim());
                    String type = tokens[4].trim();
                    boolean available = tokens[5].trim().equalsIgnoreCase("Available");
                    cars.add(new Car(id, make, model, year, type, available));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return cars;
    }
}
