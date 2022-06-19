package ru.job4j.console;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;
    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    String time = now.format(formatter);

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> chat = new ArrayList<>();
        String condition = CONTINUE;
        String userName = "User:";
        String bot = "Bot:";
        while (!OUT.equals(condition)) {
            Scanner user = new Scanner(System.in);
            String userText = user.nextLine();
            if (userText.isBlank()) {
                System.out.println("Empty input. Please re-type");
            } else {
                if (OUT.equals(userText)) {
                chat.add(userName + " " + userText);
                condition = userText;
                }
                if (STOP.equals(userText)) {
                    chat.add(userName + " " + userText);
                    condition = userText;
                } else if (!STOP.equals(condition) || CONTINUE.equals(userText)) {
                    if (OUT.equals(userText)) {
                        condition = userText;
                    } else {
                        condition = CONTINUE;
                        chat.add(userName + " " + userText);
                        Random randomBotText = new Random();
                        String botText = readPhrases().get(randomBotText.nextInt(readPhrases().size()));
                        System.out.println(botText);
                        chat.add(bot + " " + botText);
                    }
                }
            }
        }
        saveLog(chat);
    }

    private List<String> readPhrases() {
        List<String> botTextList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(botAnswers, StandardCharsets.UTF_8))) {
            br.lines().forEach(botTextList::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return botTextList;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pr = new PrintWriter(new FileWriter(path, StandardCharsets.UTF_8, true))) {
            log.forEach(pr::println);
            pr.println("### Chat ended on " + time);
            pr.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String path = "C:\\projects\\job4j_design\\src\\main\\java\\ru\\job4j\\console\\chatLog.txt";
        String botAnswers = "C:\\projects\\job4j_design\\src\\main\\java\\ru\\job4j\\console\\botAnswers.txt";
        ConsoleChat cc = new ConsoleChat(path, botAnswers);
        cc.run();
    }
}
