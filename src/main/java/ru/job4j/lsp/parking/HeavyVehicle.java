package ru.job4j.lsp.parking;


import java.util.Objects;

public class HeavyVehicle implements Vehicle {
    private String name;
    private String plateNumber;
    private int size;

    public HeavyVehicle(String name, String plateNumber, int size) {
        validateHeavyVehicleSize(size);
        this.name = name;
        this.plateNumber = plateNumber;
        this.size = size;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public void setSize(int size) {
        this.size = size;
    }


    public void validateHeavyVehicleSize(int size) {
        if (size <= LightVehicle.SIZE) {
            throw new IllegalArgumentException("Invalid size");
        }
    }

    @Override
    public int getSize() {
        return size;
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
        if (!(o instanceof HeavyVehicle)) {
            return false;
        }
        HeavyVehicle that = (HeavyVehicle) o;
        return getSize() == that.getSize() && Objects.equals(getName(), that.getName())
                && Objects.equals(getPlateNumber(), that.getPlateNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPlateNumber(), getSize());
    }

    @Override
    public String toString() {
        return "HeavyVehicle{"
                + "name='" + name + '\''
                + ", plateNumber='" + plateNumber + '\''
                + ", size=" + size
                + '}';
    }
}
