package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        Search s = new Search();
        if (s.validateArgs(args)) {
            Path start = Paths.get(args[0]);
            search(start, f -> f.toFile().getName().endsWith(args[1])).forEach(System.out::println);
        }
    }

    public boolean validateArgs(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Please provide both parameters");
        }
        if (args[0] == null) {
            throw new IllegalArgumentException("Please provide a valid directory");
        }
        if (args[1].startsWith(".")) {
            throw new IllegalArgumentException("Please provide a valid file extension");
        } else {
            return true;
        }
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}
