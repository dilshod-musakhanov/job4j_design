package ru.job4j.io.dublicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    HashMap<FileProperty, List<Path>> map = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileP = new FileProperty(Files.size(file), file.getFileName().toString());
        List<Path> list;
        if (map.containsKey(fileP)) {
            list = new ArrayList<>(map.get(fileP));
        } else {
            list = new ArrayList<>();
        }
        list.add(file.toAbsolutePath());
        map.put(fileP, list);
        return super.visitFile(file, attrs);
    }

    public void printDupeFiles() {
        for (FileProperty file : map.keySet()) {
            List<Path> list = map.get(file);
            if (list.size() > 1) {
                for (Path path : list) {
                    System.out.println(path);
                }
            }
        }
    }
}

