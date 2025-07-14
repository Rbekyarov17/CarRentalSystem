import io.*;
import model.*;
import service.*;
import manager.*;

import java.util.Scanner;

public class CarRentalApp {
    public static void main(String[] args) {
        CarFileReader reader = new CarFileReader("cars.csv");
        CarFileWriter writer = new CarFileWriter("cars.csv");
        CarRentalService service = new CarRentalService();
        service.loadCars(reader.readCars());
        RentalManager manager = new RentalManager(service, writer);

        System.out.println("Welcome to the Car Rental System");
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            System.out.print("> ");
            String command = scanner.nextLine();
            isRunning = manager.execute(command);
        }

        writer.writeCars(service.getCars());
        System.out.println("Exiting system. Goodbye!");
    }
}
