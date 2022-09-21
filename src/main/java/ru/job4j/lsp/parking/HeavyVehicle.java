package ru.job4j.lsp.parking;


public class HeavyVehicle implements Vehicle {
    private int size;
    private String plateNumber;
    private String name;

    public HeavyVehicle(String name, String plateNumber, int size) {
        this.size = size;
        this.plateNumber = plateNumber;
        this.name = name;
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
}
