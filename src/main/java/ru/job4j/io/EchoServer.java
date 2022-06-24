package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStreamWriter SocketWriter = (new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8))) {
                    String word = in.readLine();
                    if (word.contains("Hello")) {
                        SocketWriter.write("HTTP/1.1 200 OK\r\n\r\n");
                        SocketWriter.write("Hello, dear friend! Привет дружище!");
                    }
                    if (word.contains("Exit")) {
                        SocketWriter.write("HTTP/1.1 200 OK\r\n\r\n");
                        SocketWriter.write("Server closed. Завершить работу сервера");
                        server.close();
                    }
                    if (!word.contains("Hello") && !word.contains("Exit")) {
                        SocketWriter.write("HTTP/1.1 200 OK\r\n\r\n");
                        SocketWriter.write("What???");
                    }
                    SocketWriter.flush();
                }
            }
        }
    }
}
