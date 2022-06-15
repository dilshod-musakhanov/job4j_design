package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path source : sources) {
                zip.putNextEntry(new ZipEntry(source.toString()));
                try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(source.toFile()))) {
                    zip.write(in.readAllBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(in.readAllBytes());
            }
        } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public static ArgsName validateArgs(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("Provide all required arguments(directory, exclude and output)");
        }
        ArgsName argsName = ArgsName.of(args);
        String directory = argsName.get("d");
        String exclude = argsName.get("e");
        String output = argsName.get("o");
        if (!Path.of(directory).toFile().isDirectory()) {
            throw new IllegalArgumentException("Provide valid directory");
        }
        if (!exclude.startsWith(".")) {
            throw new IllegalArgumentException("Provide valid extension for excluded file");
        }
        if (!output.contains(".zip")) {
            throw new IllegalArgumentException("Provide valid extension for output file");
        }
        return argsName;
    }

    public static void main(String[] args) throws IOException {
        ArgsName argsName = validateArgs(args);
        List<Path> sources = Search.search(
                Paths.get(argsName.get("d")),
                file -> !file.toFile().getName().endsWith(argsName.get("e")));
        Zip zip = new Zip();
        zip.packFiles(sources, new File(argsName.get("o")));
/*        Zip zipSingle = new Zip();
        zipSingle.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );*/
    }

}
