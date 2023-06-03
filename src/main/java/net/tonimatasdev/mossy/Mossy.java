package net.tonimatasdev.mossy;

import net.tonimatasdev.mossy.network.ConnectionManager;

import java.io.IOException;

public class Mossy {
    private static final int SERVER_PORT = 25565;
    private static ConnectionManager connectionManager;

    public static void main(String[] args) {
        int port = 25565; // Puerto del servidor de Minecraft por defecto
        try {
            connectionManager = new ConnectionManager(port);
            connectionManager.start();
        } catch (IOException e) {
            System.out.println("Error starting server: " + e.getMessage());
        }
    }

    public static ConnectionManager getConnectionManager() {
        return connectionManager;
    }
}