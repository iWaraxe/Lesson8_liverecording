package by.issoft.sample.domain;

import java.time.LocalDate;

public class Car extends Vehicle {
    public static final int DEFAULT_NUMBER_OF_PASSENGERS = 4;

    private final int numberOfPassangers;

    public Car (String vin, String brand, String model, LocalDate prodDate) {
        this(vin, brand, model, prodDate,DEFAULT_NUMBER_OF_PASSENGERS);
    }
    public Car(String vin, String brand, String model, LocalDate prodDate, int numberOfPassangers) {
        super(vin, brand, model, prodDate);
        this.numberOfPassangers = numberOfPassangers;
    }

    public int getNumberOfPassangers() {
        return numberOfPassangers;
    }

    @Override
    public String getInfo() {
        return super.getInfo() + " Number of Passengers: " + this.getNumberOfPassangers();
    }

    @Override
    public boolean allowChildren() {
        return true;
    }

    @Override
    public String toString() {
        return getInfo();
    }
}
