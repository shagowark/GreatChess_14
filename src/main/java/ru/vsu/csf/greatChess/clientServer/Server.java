package ru.vsu.csf.greatChess.clientServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        Server server = new Server(7777);
        server.start();
    }

    private final int port;

    public Server(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        System.out.printf("Server started on: %d%n", port);
        ServerSocket serverSocket = new ServerSocket(port);
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.printf("Client connected from: %s%n", socket.getInetAddress());
            GameSession session = new GameSession(socket);
            new Thread(session).start();
        }
    }
}
