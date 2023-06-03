package net.tonimatasdev.mossy;

import net.tonimatasdev.mossy.network.Packet;
import net.tonimatasdev.mossy.network.PacketBuffer;
import net.tonimatasdev.mossy.network.packet.client.login.LoginPluginRequestPacket;
import net.tonimatasdev.mossy.network.packet.client.login.LoginSuccessPacket;
import net.tonimatasdev.mossy.network.packet.client.status.StatusResponsePacket;
import net.tonimatasdev.mossy.network.packet.server.handshake.HandshakePacket;
import net.tonimatasdev.mossy.network.packet.server.login.LoginPluginResponsePacket;
import net.tonimatasdev.mossy.network.packet.server.login.LoginStartPacket;
import net.tonimatasdev.mossy.network.packet.server.status.StatusRequestPacket;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerConnectionHandler implements Runnable {
    private final Socket clientSocket;

    public ServerConnectionHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            // Initialize the packet buffer and input/output streams
            PacketBuffer packetBuffer = new PacketBuffer(clientSocket.getInputStream(), clientSocket.getOutputStream());

            // Handle packets from the client
            while (clientSocket.isConnected()) {
                int packetId = packetBuffer.readVarInt();
                Packet packet = createPacketById(packetId);

                if (packet != null) {
                    packet.read(packetBuffer);
                    handlePacket(packet);
                }

                // Unhandled packet, handle accordingly

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handlePacket(Packet packet) {
        // Handle the specific packet type

        if (packet instanceof HandshakePacket) {
            Packet responsePacket = new StatusRequestPacket();
            sendPacket(responsePacket, 0x01);
        } else if (packet instanceof StatusRequestPacket) {
            Packet responsePacket = new StatusResponsePacket();
            sendPacket(responsePacket, 0x02);
        } else if (packet instanceof LoginStartPacket) {
            Packet responsePacket = new LoginSuccessPacket();
            sendPacket(responsePacket, 0x04);
        } else if (packet instanceof LoginPluginRequestPacket) {
            Packet responsePacket = new LoginPluginResponsePacket();
            sendPacket(responsePacket, 0x06);
        }

        // Unknown packet received, handle accordingly
    }

    private Packet createPacketById(int packetId) {
        switch (packetId) {
            // Handshake Packets
            case 0x00 -> {
                return new HandshakePacket();
            }

            // Status Packets
            case 0x01 -> {
                return new StatusRequestPacket();
            }
            case 0x02 -> {
                return new StatusResponsePacket("xd", "1.19.4", 10, 20);
            }

            // Login Packets
            case 0x03 -> {
                return new LoginStartPacket();
            }
            case 0x04 -> {
                return new LoginSuccessPacket();
            }
            case 0x05 -> {
                return new LoginPluginRequestPacket();
            }
            case 0x06 -> {
                return new LoginPluginResponsePacket();
            }
            default -> {
                System.out.println("Unhandled packet. Packet id: " + packetId);
                return null;
            }
        }
    }

    private void sendPacket(Packet packet, int packetId) {
        try {
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            ObjectOutputStream objectOut = new ObjectOutputStream(byteOut);

            objectOut.writeInt(packetId);

            objectOut.writeObject(packet);

            byte[] packetData = byteOut.toByteArray();

            DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream());
            outputStream.writeInt(packetData.length);
            outputStream.write(packetData);
            outputStream.flush();
        } catch (IOException e) {
            System.out.println("Error sending packet: " + e.getMessage());
        }
    }
}
