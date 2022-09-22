package ru.job4j.lsp.parking;

import java.util.HashSet;
import java.util.Set;

public class BaseParking implements Parking {

    private int freeSpaceLightVehicle;
    private int freeSpaceHeavyVehicle;
    private Set<Vehicle> lightVehicles;
    private Set<Vehicle> heavyVehicles;

    public BaseParking(int lightVehicle, int heavyVehicle) {
        this.freeSpaceLightVehicle = lightVehicle;
        this.freeSpaceHeavyVehicle = heavyVehicle;
        this.lightVehicles = new HashSet<>(lightVehicle);
        this.heavyVehicles = new HashSet<>(heavyVehicle);
    }

    @Override
    public boolean getParking(Vehicle vehicle) {
        return true;
    }

    @Override
    public boolean leaveParking(Vehicle vehicle) {
        return true;
    }

    public boolean notDuplicate(Vehicle vehicle) {
        return true;
    }

}
