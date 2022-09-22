package ru.job4j.lsp.parking;

public interface Parking {
    boolean getParking(Vehicle vehicle);
    boolean leaveParking(Vehicle vehicle);
}
