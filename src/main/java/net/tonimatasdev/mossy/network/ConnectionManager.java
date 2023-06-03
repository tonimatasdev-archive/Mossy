package net.tonimatasdev.mossy.network;

import net.tonimatasdev.mossy.network.player.PlayerConnection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ConnectionManager {
    private final ServerSocket serverSocket;
    private final List<PlayerConnection> playerConnections;

    public ConnectionManager(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        playerConnections = new ArrayList<>();
    }

    public void start() {
        System.out.println("Server started. Listening on port " + serverSocket.getLocalPort() + "...");

        while (true) {
            try {
                Socket socket = serverSocket.accept();
                PlayerConnection playerConnection = new PlayerConnection(socket);
                playerConnections.add(playerConnection);

                System.out.println("Player connected: " + playerConnection.getPlayerName());

            } catch (IOException e) {
                System.out.println("Error accepting connection: " + e.getMessage());
            }
        }
    }

    public void stop() {
        try {
            for (PlayerConnection playerConnection : playerConnections) {
                playerConnection.disconnect();
            }
            serverSocket.close();
        } catch (IOException e) {
            System.out.println("Error stopping server: " + e.getMessage());
        }
    }
}
