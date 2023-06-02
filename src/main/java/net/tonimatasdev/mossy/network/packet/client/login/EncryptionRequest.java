package net.tonimatasdev.mossy.network.packet.client.login;

import net.tonimatasdev.mossy.network.Packet;
import net.tonimatasdev.mossy.network.PacketBuffer;

import java.io.IOException;

public class EncryptionRequest implements Packet {
    public static int packetId = 0x01;
    private String serverId;
    private int keyLength;
    private byte[] key;
    private int tokenLength;
    private byte[] token;

    public EncryptionRequest(String serverId, int keyLength, byte[] key, int tokenLength, byte[] token) {
        this.serverId = serverId;
        this.keyLength = keyLength;
        this.key = key;
        this.tokenLength = tokenLength;
        this.token = token;

    }

    @Override
    public void write(PacketBuffer packetBuffer) throws IOException {
        packetBuffer.writeString(serverId);
        packetBuffer.writeVarInt(keyLength);
        packetBuffer.writeByteArray(key);
        packetBuffer.writeVarInt(tokenLength);
        packetBuffer.writeByteArray(token);
    }

    @Override
    public void read(PacketBuffer packetBuffer) throws IOException {
        serverId = packetBuffer.readString();
        keyLength = packetBuffer.readVarInt();
        key = packetBuffer.readByteArray();
        tokenLength = packetBuffer.readVarInt();
        token = packetBuffer.readByteArray();
    }

    public String getServerId() {
        return serverId;
    }

    public int getKeyLength() {
        return keyLength;
    }

    public byte[] getKey() {
        return key;
    }

    public int getTokenLength() {
        return tokenLength;
    }

    public byte[] getToken() {
        return token;
    }
}
