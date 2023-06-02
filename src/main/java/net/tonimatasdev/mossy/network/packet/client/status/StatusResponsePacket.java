package net.tonimatasdev.mossy.network.packet.client.status;

import net.tonimatasdev.mossy.network.Packet;
import net.tonimatasdev.mossy.network.PacketBuffer;

import java.io.IOException;

public class StatusResponsePacket implements Packet {
    private String serverName;
    private String serverVersion;
    private int onlinePlayers;
    private int maxPlayers;

    public StatusResponsePacket() {
    }

    public StatusResponsePacket(String serverName, String serverVersion, int onlinePlayers, int maxPlayers) {
        this.serverName = serverName;
        this.serverVersion = serverVersion;
        this.onlinePlayers = onlinePlayers;
        this.maxPlayers = maxPlayers;
    }

    @Override
    public void write(PacketBuffer packetBuffer) throws IOException {
        packetBuffer.writeString(serverName);
        packetBuffer.writeString(serverVersion);
        packetBuffer.writeVarInt(onlinePlayers);
        packetBuffer.writeVarInt(maxPlayers);
    }

    @Override
    public void read(PacketBuffer packetBuffer) throws IOException {
        serverName = packetBuffer.readString();
        serverVersion = packetBuffer.readString();
        onlinePlayers = packetBuffer.readVarInt();
        maxPlayers = packetBuffer.readVarInt();
    }

    public String getServerName() {
        return serverName;
    }

    public String getServerVersion() {
        return serverVersion;
    }

    public int getOnlinePlayers() {
        return onlinePlayers;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }
}