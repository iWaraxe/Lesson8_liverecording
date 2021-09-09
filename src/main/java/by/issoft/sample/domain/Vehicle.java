package by.issoft.sample.domain;

import java.time.LocalDate;
import java.util.Objects;

public abstract class Vehicle {
    private final String vin;
    private final String brand;
    private final String model;
    private final LocalDate prodDate;

    protected Vehicle(String vin, String brand, String model, LocalDate prodDate) {
        this.vin = vin;
        this.brand = brand;
        this.model = model;
        this.prodDate = prodDate;
    }

    public String getVin() {
        return vin;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public LocalDate getProdDate() {
        return prodDate;
    }

    public String getInfo() {
        return this.getBrand() + " " + this.getModel() + " " + this.getProdDate();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(vin, vehicle.vin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vin);
    }

    public abstract boolean allowChildren();
}
