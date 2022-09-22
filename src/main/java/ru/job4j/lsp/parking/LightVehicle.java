package ru.job4j.lsp.parking;


import java.util.Objects;

public class LightVehicle implements Vehicle {
    public static final int SIZE = 1;
    private String name;
    private String plateNumber;

    public LightVehicle(String name, String plateNumber) {
        this.plateNumber = plateNumber;
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    @Override
    public int getSize() {
        return SIZE;
    }

    @Override
    public String getPlateNumber() {
        return plateNumber;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LightVehicle)) {
            return false;
        }
        LightVehicle that = (LightVehicle) o;
        return getSize() == that.getSize() && Objects.equals(getPlateNumber(), that.getPlateNumber())
                && Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSize(), getPlateNumber(), getName());
    }

    @Override
    public String toString() {
        return "LightVehicle{"
                + "name='" + name + '\''
                + ", plateNumber='" + plateNumber + '\''
                + '}';
    }
}
