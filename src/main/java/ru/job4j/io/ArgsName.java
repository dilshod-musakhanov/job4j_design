package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (null == values.get(key)) {
            throw new IllegalArgumentException("Key is not found");
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        for (String a : args) {
            if (!a.contains("=")) {
                throw new IllegalArgumentException("Invalid format of parameters");
            }
            String[] temp = a.split("=", 2);
            if (temp[0].startsWith("--")
                || temp[0].substring(1).isBlank()
                || temp[1].isBlank()
            ) {
                throw new IllegalArgumentException("Invalid format of parameters");
            }
            values.put(temp[0].substring(1), temp[1]);
        }
    }

    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Empty arguments passed");
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
