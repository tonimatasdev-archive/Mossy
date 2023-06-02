package net.tonimatasdev.mossy.network.packet.server.login;

import net.tonimatasdev.mossy.network.Packet;
import net.tonimatasdev.mossy.network.PacketBuffer;

import java.io.IOException;

public class LoginPluginResponsePacket implements Packet {
    public static int packetId = 0x03;
    private int messageId;
    private boolean successful;
    private byte[] data;

    public LoginPluginResponsePacket() {
    }

    public LoginPluginResponsePacket(int messageId, boolean successful, byte[] data) {
        this.messageId = messageId;
        this.successful = successful;
        this.data = data;
    }

    @Override
    public void write(PacketBuffer packetBuffer) throws IOException {
        packetBuffer.writeVarInt(messageId);
        packetBuffer.writeBoolean(successful);
        packetBuffer.writeByteArray(data);
    }

    @Override
    public void read(PacketBuffer packetBuffer) throws IOException {
        messageId = packetBuffer.readVarInt();
        successful = packetBuffer.readBoolean();
        data = packetBuffer.readByteArray();
    }

    public int getMessageId() {
        return messageId;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public byte[] getData() {
        return data;
    }
}