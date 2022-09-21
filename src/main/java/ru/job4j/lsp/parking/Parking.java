package ru.job4j.lsp.parking;

public interface Parking {
    boolean getParking(Vehicle vehicle);
    Vehicle leaveParking(Vehicle vehicle);
}
