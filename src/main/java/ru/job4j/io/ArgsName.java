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
        validateArgs(args);
        for (String a : args) {
            args = a.split("=", 2);
            values.put(args[0].substring(1), args[1]);
        }
    }

    private void validateArgs(String[]args) throws IllegalArgumentException {
        if (args.length == 0) {
            throw new IllegalArgumentException("Empty arguments passed");
        }
        for (String arg : args) {
            if (!arg.contains("=")
                    || !arg.startsWith("-")
                    || arg.substring(1, arg.indexOf("=")).length() == 0
                    || arg.substring(arg.indexOf("=") + 1).length() == 0
            ) {
                throw new IllegalArgumentException("Invalid format of parameters");
            }
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
