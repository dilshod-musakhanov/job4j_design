package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            Map<String, String> tempMap = read.lines()
                    .filter(line -> !line.isBlank() && !line.startsWith("#"))
                    .map(line -> line.split("=", 2))
                    .filter(lines -> {
                        if (lines.length < 2 || lines[0].isEmpty() || lines[1].isEmpty()) {
                            throw new IllegalArgumentException(
                                    "Invalid data. Please make sure that the data is accurate and valid"
                            );
                        }
                        return true;
                    })
                    .collect(Collectors.toMap(k -> k[0], v -> v[1]));
            values.putAll(tempMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        String rsl;
        if (null == values.get(key)) {
            throw new UnsupportedOperationException("Empty file or wrong key. Please re check!");
        } else {
            rsl = values.get(key);
        }
        return rsl;
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("app.properties"));
        Config config = new Config("app.properties");
        config.load();

    }
}
