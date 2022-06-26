package ru.job4j.serialization.json;

import java.util.Arrays;

public class HolidayPackage {
    private final boolean onSale;
    private final String destination;
    private final int nights;
    private final String[] inclusions;
    private final String cost;
    private final SpecialOffer discount;

    public HolidayPackage(
            boolean onSale, String destination, int nights, String[] inclusions, String cost, SpecialOffer discount
    ) {
        this.onSale = onSale;
        this.destination = destination;
        this.nights = nights;
        this.inclusions = inclusions;
        this.cost = cost;
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "HolidayPackage{"
                + "onSale=" + onSale
                + ", destination='" + destination + '\''
                + ", nights=" + nights
                + ", inclusions=" + Arrays.toString(inclusions)
                + ", cost=" + cost
                + ", discount=" + discount
                + '}';
    }
}
