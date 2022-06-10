package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class Config {
    private final String path;
    private Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            values = read.lines().
                    filter(line -> !line.startsWith("#") || line.startsWith("="))
                    .map(line -> line.split("="))
                    .filter(lines -> {
                        if (lines.length < 2 || lines[0].isEmpty() || lines[1].isEmpty()) {
                            throw new IllegalArgumentException();
                        }
                        return true;
                    })
                    .collect(Collectors.toMap(k -> k[0], v -> v[1]));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
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
    }
}
