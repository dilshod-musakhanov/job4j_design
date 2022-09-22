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
        boolean result = false;
        if (notDuplicate(vehicle)) {
            if (checkSpaceLightVehicle(vehicle)) {
                lightVehicles.add(vehicle);
                freeSpaceLightVehicle--;
                System.out.format("%s %s got parked %n", vehicle.getName(), vehicle.getPlateNumber());
                result = true;
            } else if (checkSpaceHeavyVehicle(vehicle)) {
                heavyVehicles.add(vehicle);
                freeSpaceHeavyVehicle--;
                System.out.format("%s %s got parked %n", vehicle.getName(), vehicle.getPlateNumber());
                result = true;
            } else if (checkExtraSpaceForHeavyVehicle(vehicle)) {
                lightVehicles.add(vehicle);
                freeSpaceLightVehicle = freeSpaceLightVehicle - vehicle.getSize();
                System.out.format("%s %s managed to park on the next available space %n",
                        vehicle.getName(), vehicle.getPlateNumber());
                result = true;
            }
            if (!result) {
                System.out.format("Sorry no space available for %s %s. Try your luck somewhere else... %n",
                        vehicle.getName(), vehicle.getPlateNumber());
            }
        } else {
            System.out.format("This vehicle %s %s was already parked inside! WTF who are you??? %n",
                    vehicle.getName(), vehicle.getPlateNumber());
        }
        return result;
    }

    @Override
    public boolean leaveParking(Vehicle vehicle) {
        boolean result = false;
        if (lightVehicles.contains(vehicle)) {
            if (vehicle.getSize() > LightVehicle.SIZE) {
                freeSpaceLightVehicle = freeSpaceLightVehicle - vehicle.getSize();
            }
            lightVehicles.remove(vehicle);
            System.out.format("%s %s left the parking %n", vehicle.getName(), vehicle.getPlateNumber());
            result = true;
        }
        if (heavyVehicles.contains(vehicle)) {
            freeSpaceHeavyVehicle--;
            heavyVehicles.remove(vehicle);
            System.out.format("%s %s left the parking %n", vehicle.getName(), vehicle.getPlateNumber());
            result = true;
        }
        if (!result) {
            System.out.format("%s %s has never been parked here %n", vehicle.getName(), vehicle.getPlateNumber());
        }
        return result;
    }

    public boolean notDuplicate(Vehicle vehicle) {
        return !lightVehicles.contains(vehicle) && !heavyVehicles.contains(vehicle);
    }

    public boolean checkSpaceLightVehicle(Vehicle vehicle) {
        return vehicle.getSize() == LightVehicle.SIZE && freeSpaceLightVehicle > 0;
    }

    public boolean checkSpaceHeavyVehicle(Vehicle vehicle) {
        return vehicle.getSize() > LightVehicle.SIZE && freeSpaceHeavyVehicle >= LightVehicle.SIZE;
    }

    public boolean checkExtraSpaceForHeavyVehicle(Vehicle vehicle) {
        return vehicle.getSize() > LightVehicle.SIZE
                && freeSpaceHeavyVehicle == 0 && freeSpaceLightVehicle >= vehicle.getSize();
    }

}
