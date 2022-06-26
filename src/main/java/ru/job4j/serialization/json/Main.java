package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        final Person person = new Person(false, 30, new Contact("11-111"),
                new String[]{"worker", "Married"});
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(person));
        final String personJson =
                "{"
                        + "\"sex\":false,"
                        + "\"age\":35,"
                        + "\"contact\":"
                        + "{"
                        + "\"phone\":\"+7(924)111-111-11-11\""
                        + "},"
                        + "\"statuses\":"
                        + "[\"Student\",\"Free\"]"
                        + "}";
        final Person personMod = gson.fromJson(personJson, Person.class);
        System.out.println(personMod);
        HolidayPackage holidayPackage = new HolidayPackage(
                true, "Dubai", 7,
                new String[]{"Air fare", "Accommodation", "Breakfast", "Transfers", "Safari Tour"},
                "USD 2000", new SpecialOffer("30%")
        );
        final Gson gson1 = new GsonBuilder().create();
        System.out.println(gson1.toJson(holidayPackage));
        final String holidayPackageJson =
                "{"
                        + "\"onSale\": true,"
                        + "\"destination\": Maldives,"
                        + "\"nights\": 7,"
                        + "\"inclusions\":"
                        + "[\"Air fare\",\"Accommodation\",\"All Inclusive\"],"
                        + "\"cost\": \"USD 3000\","
                        + "\"discount\":"
                        + "{"
                        + "\"discount\":\"20%\""
                        + "}"
                        + "}";
        final HolidayPackage holidayPackageMod = gson1.fromJson(holidayPackageJson, HolidayPackage.class);
        System.out.println(holidayPackageMod);
    }
}
