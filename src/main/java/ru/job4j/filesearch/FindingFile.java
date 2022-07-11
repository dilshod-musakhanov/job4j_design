package ru.job4j.filesearch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class FindingFile {

    public static void main(String[] args) throws IOException {
        ArgsValidator argsValidator = ArgsValidator.argsValidatorOf(args);
        List<Path> pathList = searchBy(argsValidator);
        writeSearchedFile(pathList, argsValidator);
    }

    private static final Logger LOG = LoggerFactory.getLogger(FindingFile.class.getName());

    private static List<Path> searchBy(ArgsValidator argsValidator) throws IOException {
        String type = argsValidator.getArgs("t");
        List<Path> searchedFiles = new ArrayList<>();
        if ("name".contains(type)) {
            searchedFiles = search(Path.of(argsValidator.getArgs("d")), f -> f.toFile()
                    .getName().equals(argsValidator.getArgs("n"))
            );
        }
        if ("regex".contains(type)) {
            Pattern pattern = Pattern.compile(argsValidator.getArgs("n"));
            searchedFiles = search(Path.of(argsValidator.getArgs("d")), f -> pattern.matcher(f.toFile()
                    .getName()).find()
            );
        }
        if ("mask".contains(type)) {
            Pattern pattern = Pattern.compile(argsValidator.getArgs("n")
                    .replace(".", "[.]")
                    .replace("*", ".*")
                    .replace("?", "."));
            searchedFiles = search(Path.of(argsValidator.getArgs("d")), f -> pattern.matcher(f.toFile()
                    .getName()).find()
            );
        }
        return searchedFiles;
    }

    public static List<Path> search(Path path, Predicate<Path> condition) throws IOException {
        SearchingFile searchingFile = new SearchingFile(condition);
        Files.walkFileTree(path, searchingFile);
        return searchingFile.getPathList();
    }

    public static void writeSearchedFile(List<Path> paths, ArgsValidator argsValidator) {
        String name = argsValidator.getArgs("n");
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(argsValidator.getArgs("o")))) {
            if (paths.isEmpty()) {
                printWriter.printf("No file found as per passed argument: %s", name);
                LOG.debug("No file found as per passed argument: {}", name);
            } else {
                for (Path f : paths) {
                    printWriter.println(f);
                }
            }
        } catch (Exception e) {
            LOG.error("Exception in writeSearchedFile()");
        }
    }
}
