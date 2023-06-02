package net.tonimatasdev.mossy.network.packet.server.handshake;

import net.tonimatasdev.mossy.network.Packet;
import net.tonimatasdev.mossy.network.PacketBuffer;

import java.io.IOException;

public class HandshakePacket implements Packet {
    public static int packetId = 0x00;
    private int protocolVersion;
    private String serverAddress;
    private int serverPort;
    private int nextState;

    public HandshakePacket() {
    }

    public HandshakePacket(int protocolVersion, String serverAddress, int serverPort, int nextState) {
        this.protocolVersion = protocolVersion;
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
        this.nextState = nextState;
    }

    @Override
    public void write(PacketBuffer packetBuffer) throws IOException {
        packetBuffer.writeVarInt(protocolVersion);
        packetBuffer.writeString(serverAddress);
        packetBuffer.writeShort((short) serverPort);
        packetBuffer.writeVarInt(nextState);
    }

    @Override
    public void read(PacketBuffer packetBuffer) throws IOException {
        protocolVersion = packetBuffer.readVarInt();
        serverAddress = packetBuffer.readString();
        serverPort = packetBuffer.readUnsignedShort();
        nextState = packetBuffer.readVarInt();
    }

    public int getProtocolVersion() {
        return protocolVersion;
    }

    public String getServerAddress() {
        return serverAddress;
    }

    public int getServerPort() {
        return serverPort;
    }

    public int getNextState() {
        return nextState;
    }
}
