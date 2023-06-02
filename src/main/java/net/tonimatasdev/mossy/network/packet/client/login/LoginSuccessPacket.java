package net.tonimatasdev.mossy.network.packet.client.login;

import net.tonimatasdev.mossy.network.Packet;
import net.tonimatasdev.mossy.network.PacketBuffer;

import java.io.IOException;

public class LoginSuccessPacket implements Packet {
    public static int packetId = 0x02;
    private String uuid;
    private String username;

    public LoginSuccessPacket() {
    }

    public LoginSuccessPacket(String uuid, String username) {
        this.uuid = uuid;
        this.username = username;
    }

    public String getUUID() {
        return uuid;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public void write(PacketBuffer packetBuffer) throws IOException {
        packetBuffer.writeString(uuid);
        packetBuffer.writeString(username);
    }

    @Override
    public void read(PacketBuffer packetBuffer) throws IOException {
        uuid = packetBuffer.readString();
        username = packetBuffer.readString();
    }
}







