package interfaces;

import model.Car;
import model.Customer;
import java.time.LocalDate;

public interface Rentable {
    boolean rentCar(int carId, Customer customer, LocalDate startDate, LocalDate returnDate);
    boolean returnCar(int carId);
}
