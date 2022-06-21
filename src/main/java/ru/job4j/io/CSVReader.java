package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class CSVReader {

    public static void validateArgs(String path, String delimiter, String target, Set<String> filterElements) {
        Path source = Paths.get(path);
        if (!path.endsWith(".csv") || !source.toFile().exists()) {
            throw new IllegalArgumentException("Root file is not valid");
        }
        if (!";".equals(delimiter)) {
            throw new IllegalArgumentException("Delimeter passed does not match to ';'");
        }
        if (!"stdout".equals(target)
                && (!(new File(target).exists())
                || !(new File(target).isFile()))) {
            throw new IllegalArgumentException("Invalid file or stdout");
        }
        if (filterElements.size() == 0) {
            throw new IllegalArgumentException("Filter elements are empty");
        }
    }

    public static void handle(ArgsName argsName) throws Exception {
        String path = argsName.get("path");
        String delimiter = argsName.get("delimiter");
        String target = argsName.get("out");
        Set<String> filterElements = Arrays.stream(argsName.get("filter")
                .split(","))
                .collect(Collectors.toSet());
        validateArgs(path, delimiter, target, filterElements);
        try (Scanner scanner = new Scanner(new FileReader(path));
             PrintWriter printWriter = "stdout".equals(target)
                     ? new PrintWriter(System.out)
                     : new PrintWriter(new FileWriter(target))) {
            String[] titles = scanner.nextLine().split(delimiter);
            List<String[]> mainData = new ArrayList<>();
            while (scanner.hasNext()) {
                mainData.add(scanner.nextLine().split(delimiter));
            }

            StringBuilder strTitle = new StringBuilder();
            for (int i = 0; i < titles.length; i++) {
                if (filterElements.contains(titles[i])) {
                    strTitle.append(strTitle.length() == 0 ? titles[i] : delimiter + titles[i]);
                }
            }
            printWriter.println(strTitle);
            for (String[] data : mainData) {
                StringBuilder strData = new StringBuilder();
                for (int i = 0; i < titles.length; i++) {
                    if (filterElements.contains(titles[i])) {
                        strData.append(strData.length() == 0 ? data[i] : delimiter + data[i]);
                    }
                }
                printWriter.println(strData);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        ArgsName argsName = ArgsName.of(args);
        CSVReader.handle(argsName);
    }
}
