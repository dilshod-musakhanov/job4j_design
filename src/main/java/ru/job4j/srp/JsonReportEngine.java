package ru.job4j.srp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.function.Predicate;

public class JsonReportEngine implements Report {

    private Store store;

    private Gson gson = new GsonBuilder().create();

    public JsonReportEngine(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        return gson.toJson(store.findBy(filter));
    }

}
