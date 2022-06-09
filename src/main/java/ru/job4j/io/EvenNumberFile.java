package ru.job4j.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int data;
            while ((data = in.read()) != -1) {
                text.append((char) data);
            }
            String[] numbers = text.toString().split(System.lineSeparator());
            for (String number : numbers) {
                if (Integer.parseInt(number) % 2 == 0) {
                    System.out.println(number + " - is even number");
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
