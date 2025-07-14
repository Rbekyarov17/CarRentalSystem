package model;

import java.time.LocalDate;

public class Rental {
    private Car car;
    private Customer customer;
    private LocalDate startDate;
    private LocalDate expectedReturn;

    public Rental(Car car, Customer customer, LocalDate startDate, LocalDate expectedReturn) {
        this.car = car;
        this.customer = customer;
        this.startDate = startDate;
        this.expectedReturn = expectedReturn;
    }

    // Getters and custom methods
}
