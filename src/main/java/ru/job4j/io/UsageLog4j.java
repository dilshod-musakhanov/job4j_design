package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Petr Arsantev";
        int age = 33;
        LOG.debug("User info name : {}, age : {}", name, age);
        String ads = "Selling My Car";
        String model = "BWM X6";
        double mileage = 12.000;
        long cost = 10000;
        boolean isUsed = true;
        boolean hadAccident = false;
        int year = 2010;
        LOG.debug("User advertisement : {}, Model : {}, Year : {}, Price $USD : {}",  ads, model, year, cost);
        LOG.debug("More details : Mileage : {}, Used : {}, Injured in accident : {}", mileage, isUsed, hadAccident);
    }
}
