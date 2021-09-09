package by.issoft.sample.domain;

import java.time.LocalDate;

public class Truck extends Vehicle {

    private final int weight;

    public Truck(String vin, String brand, String model, LocalDate prodDate, int weight) {
        super(vin, brand, model, prodDate);
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String getInfo() {
        return super.getInfo() + " Weight: " + this.getWeight();
    }

    @Override
    public boolean allowChildren() {
        return false;
    }
}
