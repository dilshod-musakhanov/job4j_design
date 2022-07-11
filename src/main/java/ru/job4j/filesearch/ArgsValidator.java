package ru.job4j.filesearch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Path;
import java.util.Map;
import java.util.HashMap;

public class ArgsValidator {

    private final Map<String, String> argsValues = new HashMap<>();

    private static final Logger LOG = LoggerFactory.getLogger(ArgsValidator.class.getName());

    private void validateAllArgs(String[] args) {
        if (args.length != 4) {
            LOG.error("Not all arguments are passed");
            throw new IllegalArgumentException("Provide all required arguments");
        }
        for (String a: args) {
            if (!a.contains("=")
                    || !a.startsWith("-")
                    || a.substring(1, a.indexOf("=")).length() == 0
                    || a.substring(a.indexOf("="), a.indexOf("=") + 1).length() == 0
            ) {
                LOG.error("Invalid format of parameters");
                throw new IllegalArgumentException("Provide valid format of parameters");
            }
        }
    }

    private void parse(String[] args) {
        validateAllArgs(args);
        for (String a : args) {
            args = a.split("=", 2);
            argsValues.put(args[0].substring(1), args[1]);
        }
        validateRootAndTarget(argsValues);
    }

    private static void validateRootAndTarget(Map<String, String> argsValues) {
        Path root = Path.of(argsValues.get("d"));
        String target = argsValues.get("o");
        String type = argsValues.get("t");
        if (!root.toFile().exists()) {
            LOG.error("Directory is not valid");
            throw new IllegalArgumentException("Directory is not valid");
        }
        if (!target.endsWith(".txt")) {
            LOG.error("File is not in txt format");
            throw new IllegalArgumentException("File has to be in txt format");
        }
        if (!(new File(target).exists())) {
            LOG.error("Target file does not exist");
            throw new IllegalArgumentException("File does not exist. Create target file or use log.txt");
        }
        switch (type) {
            case "name":
                LOG.debug("Type passed: {}", type);
                break;
            case "mask":
                LOG.debug("Type passed: {}", type);
                break;
            case "regex":
                LOG.debug("Type passed: {}", type);
                break;
            default:
                LOG.error("Invalid type passed: {}", type);
                throw new IllegalArgumentException("The type (-t=) has to be one of these: name, mask or regex");
        }
    }

    public String getArgs(String key) {
        if (null == argsValues.get(key)) {
            LOG.error("Key is not found");
            throw new IllegalArgumentException("Key is not found");
        }
        return argsValues.get(key);
    }

    public static ArgsValidator argsValidatorOf(String[] args) {
        if (args.length == 0) {
            LOG.error("Empty arguments passed");
            throw new IllegalArgumentException("Empty arguments passed");
        }
        ArgsValidator argsValidator = new ArgsValidator();
        argsValidator.parse(args);
        return argsValidator;
    }
}
