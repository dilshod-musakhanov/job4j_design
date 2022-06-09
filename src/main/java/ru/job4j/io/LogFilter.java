package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {
    public List<String> filter(String file) {
        List<String> str = null;
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            str = in.lines()
                    .filter(line -> line.substring(line.lastIndexOf('"'), line.lastIndexOf(' '))
                            .contains("404"))
                    .collect(Collectors.toList());
            if (str.isEmpty()) {
                str = null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("log.txt");
        System.out.println(log);
    }
}
