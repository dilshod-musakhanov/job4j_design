package ru.job4j.io;

import java.io.File;

import static ru.job4j.io.Search.validateArgs;

public class Dir {
    public static void main(String[] args) {
        validateArgs(args);
        File file = new File(args[0]);
        System.out.println(String.format("size : %s", file.getTotalSpace()));
        for (File subfile : file.listFiles()) {
            System.out.println(String.format("Name: %s  Size: %d", subfile.getName(), subfile.length()));
        }
    }
}
