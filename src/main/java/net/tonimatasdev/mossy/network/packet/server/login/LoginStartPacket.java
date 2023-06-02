package net.tonimatasdev.mossy.network.packet.server.login;

import net.tonimatasdev.mossy.network.Packet;
import net.tonimatasdev.mossy.network.PacketBuffer;

import java.io.IOException;

public class LoginStartPacket implements Packet {
    public static int packetId = 0x02;
    private String username;
    //private boolean hasUUID;
    //private String playerUUID;

    public LoginStartPacket() {
    }

    public LoginStartPacket(String username) {
        this.username = username;
    }

    @Override
    public void write(PacketBuffer packetBuffer) throws IOException {
        packetBuffer.writeString(username);
    }

    @Override
    public void read(PacketBuffer packetBuffer) throws IOException {
        username = packetBuffer.readString();
    }

    public String getUsername() {
        return username;
    }
}
