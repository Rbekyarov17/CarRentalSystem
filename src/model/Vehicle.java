package model;

public abstract class Vehicle {
    protected int id;
    protected String make;
    protected String model;
    protected int year;
    protected String type;

    public Vehicle(int id, String make, String model, int year, String type) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;
        this.type = type;
    }

    public abstract String getDetails();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
