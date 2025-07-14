# 🚗 Car Rental System (Java Console App)
Car Rental System is a console-based Java application that enables rental agencies to manage a fleet of cars, handle rentals and returns, and maintain records using file-based storage. The project demonstrates core Object-Oriented Programming (OOP) principles like encapsulation, inheritance, polymorphism, and abstraction.

---

## 🎯 Features

- Add, edit, remove, and list cars
- Rent and return cars with customer and date tracking
- Search cars by ID, model, or availability
- File-based data persistence using custom CSV logic
- Implements OOP principles: encapsulation, inheritance, polymorphism, abstraction
- Error handling for invalid inputs and file operations

---

## 🧠 Object-Oriented Structure

- `Car` and `Vehicle`: Base classes with inheritance
- `Customer`: Represents rental customer
- `Rental`: Represents a car rental record
- `CarRentalService`: Manages inventory and rental logic
- `CarFileReader` / `CarFileWriter`: Handles CSV file I/O
- `RentalManager`: Handles user input and command execution
- Interfaces: `Rentable`, `Searchable`
- Abstract class: `Vehicle` for common vehicle properties

---

## 🧭 User Commands
At the console prompt, you can enter:

| Command       | Description                        |
| ------------- | ---------------------------------- |
| `add`         | Add a new car                      |
| `edit <id>`   | Edit car details                   |
| `remove <id>` | Remove (flag) a car from inventory |
| `list`        | List all cars                      |
| `rent`        | Rent a car to a customer           |
| `return <id>` | Return a rented car                |
| `search`      | Search by model, ID, or status     |
| `save`        | Save data to file                  |
| `exit`        | Exit the app without saving        |
| `saveexit`    | Save and exit                      |
| `help`        | Show command help                  |

---

Data is saved automatically when you run save or saveexit

Rental history is tracked in a separate CSV file

You can edit the CSV files manually — just keep the format clean!

