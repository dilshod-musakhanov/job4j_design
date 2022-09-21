package ru.job4j.lsp.parking;

import org.junit.Test;
import org.junit.jupiter.api.Disabled;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.*;

@Disabled
public class ParkingTest {

    @Test
    public void when1LightVehicleGetParking() {
        BaseParking baseParking = new BaseParking(2, 2);
        Vehicle lightVehicle = new LightVehicle("Spark", "AA111", 1);
        assertThat(baseParking.getParking(lightVehicle)).isEqualTo(true);
    }

    @Test
    public void when1HeavyVehicleGetParking() {
        BaseParking baseParking = new BaseParking(2, 2);
        Vehicle heavyVehicle = new HeavyVehicle("Zil", "AA90", 2);
        assertThat(baseParking.getParking(heavyVehicle)).isEqualTo(true);
    }

    @Test
    public void when2LightVehicleAndOnly1GetParking() {
        BaseParking baseParking = new BaseParking(1, 2);
        Vehicle lightVehicle1 = new LightVehicle("Spark", "AA111", 1);
        Vehicle lightVehicle2 = new LightVehicle("Kia", "AA222", 1);
        assertThat(baseParking.getParking(lightVehicle1)).isEqualTo(true);
        assertThat(baseParking.getParking(lightVehicle2)).isEqualTo(false);
    }

    @Test
    public void when2HeavyVehicleAndOnly1GetParking() {
        BaseParking baseParking = new BaseParking(1, 1);
        Vehicle lightVehicle1 = new LightVehicle("Spark", "AA111", 1);
        Vehicle heavyVehicle1 = new HeavyVehicle("Zil", "AA90", 2);
        Vehicle heavyVehicle2 = new HeavyVehicle("Kamaz", "AA10", 2);
        assertThat(baseParking.getParking(lightVehicle1)).isEqualTo(true);
        assertThat(baseParking.getParking(heavyVehicle1)).isEqualTo(true);
        assertThat(baseParking.getParking(heavyVehicle2)).isEqualTo(false);
    }

    @Test
    public void whenNoSpaceThenHeavyVehicleGetParkingOnFreeSpaceOfLightVehicle() {
        BaseParking baseParking = new BaseParking(2, 1);
        Vehicle heavyVehicle1 = new HeavyVehicle("Zil", "AA90", 2);
        Vehicle heavyVehicle2 = new HeavyVehicle("Kamaz", "AA10", 2);
        assertThat(baseParking.getParking(heavyVehicle1)).isEqualTo(true);
        assertThat(baseParking.getParking(heavyVehicle2)).isEqualTo(true);
    }

    @Test
    public void whenLeaveParkingIsTheSameLightVehicle() {
        BaseParking baseParking = new BaseParking(2, 2);
        Vehicle lightVehicle1 = new LightVehicle("Spark", "AA111", 1);
        Vehicle lightVehicle2 = new LightVehicle("Kia", "AA222", 1);
        baseParking.getParking(lightVehicle1);
        baseParking.getParking(lightVehicle2);
        assertThat(baseParking.leaveParking(lightVehicle1)).isEqualTo(lightVehicle1);
    }

    @Test
    public void whenLeaveParkingIsTheSameHeavyVehicle() {
        BaseParking baseParking = new BaseParking(2, 2);
        Vehicle heavyVehicle1 = new HeavyVehicle("Zil", "AA90", 2);
        Vehicle heavyVehicle2 = new HeavyVehicle("Kamaz", "AA10", 2);
        baseParking.getParking(heavyVehicle1);
        baseParking.getParking(heavyVehicle2);
        assertThat(baseParking.leaveParking(heavyVehicle1)).isEqualTo(heavyVehicle1);
    }

}