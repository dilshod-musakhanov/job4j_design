package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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
        System.out.println();

        JSONObject jsonContact = new JSONObject("{\"phone\":\"+7(924)111-111-11-11\"}");
        List<String> list = new ArrayList<>();
        list.add("Student");
        list.add("Free");
        JSONArray jsonStatuses = new JSONArray(list);
        final Person person1 =  new Person(
                false, 30, new Contact("11-111"),
                new String[]{"Worker", "Married"}
        );
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sex", person1.isSex());
        jsonObject.put("age", person1.getAge());
        jsonObject.put("contact", jsonContact);
        jsonObject.put("statuses", jsonStatuses);
        System.out.println(jsonObject.toString());
        System.out.println(new JSONObject(person1).toString());
        System.out.println();

        /* JSONObject из json-строки строки */
        JSONObject jsonDiscount = new JSONObject("{\"discount\":\"15%\"}");

        /* JSONArray из ArrayList */
        List<String> listInc = new ArrayList<>();
        listInc.add("Air fare");
        listInc.add("Accommodation");
        listInc.add("Breakfast");
        JSONArray jsonInclusions = new JSONArray(listInc);

        /* JSONObject напрямую методом put */
        final HolidayPackage holidayPackageDubai = new HolidayPackage(
                true, "Dubai", 5,
                new String[]{"Air fare", "Accommodation", "Breakfast", "Transfers", "Safari Tour"},
                "USD 1800", new SpecialOffer("30%")
        );
        JSONObject jsonObjectHolPack = new JSONObject();
        jsonObjectHolPack.put("onSale", holidayPackageDubai.isOnSale());
        jsonObjectHolPack.put("destination", holidayPackageDubai.getDestination());
        jsonObjectHolPack.put("nights", holidayPackageDubai.getNights());
        jsonObjectHolPack.put("inclusions", jsonInclusions);
        jsonObjectHolPack.put("cost", holidayPackageDubai.getCost());
        jsonObjectHolPack.put("discount", jsonDiscount);
        System.out.println(jsonObjectHolPack.toString());
        System.out.println(new JSONObject(holidayPackageDubai).toString());
    }
}
