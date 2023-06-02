package net.tonimatasdev.mossy.network.packet.client.login;

import net.tonimatasdev.mossy.network.Packet;
import net.tonimatasdev.mossy.network.PacketBuffer;

import java.io.IOException;

public class LoginPluginRequestPacket implements Packet {
    public static int packetId = 0x04;
    private int messageId;
    private String channel;
    private byte[] data;

    public LoginPluginRequestPacket() {
    }

    public LoginPluginRequestPacket(int messageId, String channel, byte[] data) {
        this.messageId = messageId;
        this.channel = channel;
        this.data = data;
    }

    @Override
    public void write(PacketBuffer packetBuffer) throws IOException {
        packetBuffer.writeVarInt(messageId);
        packetBuffer.writeString(channel);
        packetBuffer.writeByteArray(data);
    }

    @Override
    public void read(PacketBuffer packetBuffer) throws IOException {
        messageId = packetBuffer.readVarInt();
        channel = packetBuffer.readString();
        data = packetBuffer.readByteArray();
    }

    public int getMessageId() {
        return messageId;
    }

    public String getChannel() {
        return channel;
    }

    public byte[] getData() {
        return data;
    }
}







