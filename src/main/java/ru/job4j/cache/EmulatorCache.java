package ru.job4j.cache;

import java.util.Scanner;

public class EmulatorCache {

    DirFileCache dirFileCache;
    Scanner scanner = new Scanner(System.in);

    public static final int ADD_DIRECTORY = 1;
    public static final int LOAD_TO_CACHE = 2;
    public static final int GET_CONTENT_FROM_CACHE = 3;
    public static final String SELECT = "Выберите меню: ";
    public static final String DIRECTORY = "Введите директорию";
    public static final String FILE = "Введите имя файла";
    public static final String EXECUTED = "Операция завершена успешно";
    public static final String CONTENT = "Содержимое файла:";
    public static final String EXIT = "Конец работы";

    public static final String MENU = """
            Введите 1, для указания кэшируемую директорию.
            Введите 2, чтобы загрузить содержимое файла в кэш.
            Введите 3, чтобы получить содержимое файла из кэша.
            Введите любое другое число для выхода.
            """;

    private void insertDir() {
        System.out.println(DIRECTORY);
        String dir = scanner.nextLine();
        dirFileCache = new DirFileCache(dir);
        System.out.println(EXECUTED);
    }

    private void loadToCache() {
        System.out.println(FILE);
        String file = scanner.nextLine();
        dirFileCache.put(file, dirFileCache.load(file));
        System.out.println(EXECUTED);
    }

    private void getFromCache() {
        System.out.println(FILE);
        String file = scanner.nextLine();
        String res = dirFileCache.get(file);
        System.out.println(CONTENT);
        System.out.println(res);
    }

    private void start() {
        boolean run = true;
        while (run) {
            System.out.println(MENU);
            System.out.print(SELECT);
            int option = Integer.parseInt(scanner.nextLine());
            if (ADD_DIRECTORY == option) {
                insertDir();
            } else if (LOAD_TO_CACHE == option) {
                loadToCache();
            } else if (GET_CONTENT_FROM_CACHE == option) {
                getFromCache();
            } else {
                run = false;
                System.out.println(EXIT);
            }
        }
    }

    public static void main(String[] args) {
        EmulatorCache emulatorCache = new EmulatorCache();
        emulatorCache.start();
    }
}
