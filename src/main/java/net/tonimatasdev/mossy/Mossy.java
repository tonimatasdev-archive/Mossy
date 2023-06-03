package net.tonimatasdev.mossy;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@SuppressWarnings({"InfiniteLoopStatement", "resource"})
public class Mossy {
    private static final int SERVER_PORT = 25565;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
            System.out.println("Server started on port " + SERVER_PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

                ServerConnectionHandler connectionHandler = new ServerConnectionHandler(clientSocket);
                Thread thread = new Thread(connectionHandler);
                thread.start();
            }
        } catch (IOException e) {
            System.out.println("Error starting server: " + e.getMessage());
        }
    }
}