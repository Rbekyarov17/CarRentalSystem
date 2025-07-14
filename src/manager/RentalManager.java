package manager;

import model.*;
import service.CarRentalService;
import io.CarFileWriter;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class RentalManager {
    private CarRentalService service;
    private CarFileWriter writer;

    public RentalManager(CarRentalService service, CarFileWriter writer) {
        this.service = service;
        this.writer = writer;
    }

    public boolean execute(String command) {
        Scanner input = new Scanner(System.in);
        try {
            String[] tokens = command.split(" ");

            switch (tokens[0].toLowerCase()) {
                case "add":
                    System.out.println("Enter Car details: id, make, model, year, type, availability");
                    String[] carData = input.nextLine().split(",");
                    if (carData.length < 6) {
                        System.out.println("Invalid car data.");
                        return true;
                    }
                    int id = Integer.parseInt(carData[0].trim());
                    String make = carData[1].trim();
                    String model = carData[2].trim();
                    int year = Integer.parseInt(carData[3].trim());
                    String type = carData[4].trim();
                    boolean available = carData[5].trim().equalsIgnoreCase("Available");

                    Car car = new Car(id, make, model, year, type, available);
                    service.addCar(car);
                    System.out.println("Car added successfully.");
                    break;

                case "edit":
                    if (tokens.length < 2) {
                        System.out.println("Usage: edit <carId>");
                        break;
                    }
                    int editId = Integer.parseInt(tokens[1]);
                    Car carToEdit = service.searchById(editId);
                    if (carToEdit == null) {
                        System.out.println("Car not found.");
                        break;
                    }
                    System.out.println("Enter updated make, model, year, type, availability:");
                    String[] updated = input.nextLine().split(",");
                    carToEdit.setMake(updated[0].trim());
                    carToEdit.setModel(updated[1].trim());
                    carToEdit.setYear(Integer.parseInt(updated[2].trim()));
                    carToEdit.setType(updated[3].trim());
                    carToEdit.setAvailable(updated[4].trim().equalsIgnoreCase("Available"));
                    System.out.println("Car updated successfully.");
                    break;

                case "remove":
                    if (tokens.length < 2) {
                        System.out.println("Usage: remove <carId>");
                        break;
                    }
                    int removeId = Integer.parseInt(tokens[1]);
                    if (service.removeCar(removeId)) {
                        System.out.println("Car removed (flagged unavailable).");
                    } else {
                        System.out.println("Car not found.");
                    }
                    break;

                case "list":
                    List<Car> allCars = service.getCars();
                    for (Car c : allCars) {
                        System.out.println(c.getDetails());
                    }
                    break;

                case "rent":
                    System.out.println("Enter: carId, customer name, rental start date (yyyy-MM-dd), return date");
                    String[] rentData = input.nextLine().split(",");
                    int carId = Integer.parseInt(rentData[0].trim());
                    String name = rentData[1].trim();
                    LocalDate start = LocalDate.parse(rentData[2].trim());
                    LocalDate returnDate = LocalDate.parse(rentData[3].trim());

                    Customer customer = new Customer(name, "C" + System.currentTimeMillis());
                    boolean success = service.rentCar(carId, customer, start, returnDate);
                    System.out.println(success ? "Car rented successfully." : "Failed to rent car.");
                    break;

                case "return":
                    if (tokens.length < 2) {
                        System.out.println("Usage: return <carId>");
                        break;
                    }
                    int returnId = Integer.parseInt(tokens[1]);
                    boolean returned = service.returnCar(returnId);
                    System.out.println(returned ? "Car returned successfully." : "Failed to return car.");
                    break;

                case "search":
                    if (tokens.length < 3) {
                        System.out.println("Usage: search model|id|status <value>");
                        break;
                    }
                    switch (tokens[1].toLowerCase()) {
                        case "model":
                            List<Car> byModel = service.searchByModel(tokens[2]);
                            for (Car c : byModel) System.out.println(c.getDetails());
                            break;
                        case "id":
                            Car foundCar = service.searchById(Integer.parseInt(tokens[2]));
                            System.out.println(foundCar != null ? foundCar.getDetails() : "Car not found.");
                            break;
                        case "status":
                            boolean status = tokens[2].equalsIgnoreCase("available");
                            List<Car> byStatus = service.searchByStatus(status);
                            for (Car c : byStatus) System.out.println(c.getDetails());
                            break;
                        default:
                            System.out.println("Invalid search type.");
                    }
                    break;

                case "save":
                case "exit":
                case "saveexit":
                    writer.writeCars(service.getCars());
                    System.out.println("Data saved. Exiting...");
                    return false;

                case "help":
                    printHelp();
                    break;

                default:
                    System.out.println("Unknown command. Type 'help' for list of commands.");
            }

        } catch (NumberFormatException | DateTimeParseException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid input: " + e.getMessage());
        }

        return true;
    }

    private void printHelp() {
        System.out.println("""
            Available commands:
            add                  -> Add a new car
            edit <carId>         -> Edit existing car info
            remove <carId>       -> Flag a car as removed
            list                 -> List all cars
            rent                 -> Rent a car to customer
            return <carId>       -> Return a rented car
            search model <name>  -> Search by model
            search id <id>       -> Search by car ID
            search status <val>  -> Search by availability (Available/Rented)
            save | exit          -> Save and exit
            help                 -> Display commands
        """);
    }
}
