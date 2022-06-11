package ru.job4j.io;

import java.io.*;

public class Analizy {
    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source));
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(target)))
            ) {
            boolean serviceDown = true;
            String openTime = null;
            String closTime = null;

            for (String line = in.readLine(); line != null; line = in.readLine()) {
                String[] st;
                st = line.split(" ");

                if ((st[0].equals("400") || st[0].equals("500")) && serviceDown) {
                    openTime = st[1];
                    serviceDown = false;
                }
                if ((st[0].equals("200") || st[0].equals("300")) && !serviceDown) {
                    closTime = st[1];
                    serviceDown = true;
                }
                if (openTime != null && closTime != null) {
                    String time = openTime + " ; " + closTime;
                    out.println(time);
                    openTime = null;
                    closTime = null;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        String source = "./data/s.txt";
        String target = "./data/t.txt";
        Analizy analizy = new Analizy();
        analizy.unavailable(source, target);
    }
}
