package interfaces;

import model.Car;
import java.util.List;

public interface Searchable {
    List<Car> searchByModel(String model);
    Car searchById(int id);
    List<Car> searchByStatus(boolean isAvailable);
}
