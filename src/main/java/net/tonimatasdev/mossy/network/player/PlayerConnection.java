package net.tonimatasdev.mossy.network.player;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class PlayerConnection {
    private final Socket socket;
    private final DataInputStream inputStream;
    private final DataOutputStream outputStream;
    private final String playerName;

    public PlayerConnection(Socket socket) throws IOException {
        this.socket = socket;
        inputStream = new DataInputStream(socket.getInputStream());
        outputStream = new DataOutputStream(socket.getOutputStream());
        playerName = "";
    }

    public String getPlayerName() {
        return playerName;
    }

    public void disconnect() {
        try {
            inputStream.close();
            outputStream.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("Error closing player connection: " + e.getMessage());
        }
    }
}

