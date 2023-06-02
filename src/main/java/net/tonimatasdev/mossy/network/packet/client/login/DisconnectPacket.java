package net.tonimatasdev.mossy.network.packet.client.login;

import net.tonimatasdev.mossy.network.Packet;
import net.tonimatasdev.mossy.network.PacketBuffer;

import java.io.IOException;

public class DisconnectPacket implements Packet {
    public static int packetId = 0x00;
    private String reason;

    public DisconnectPacket(String reason) {
        this.reason = reason;
    }

    @Override
    public void write(PacketBuffer packetBuffer) throws IOException {
        packetBuffer.writeString(reason);
    }

    @Override
    public void read(PacketBuffer packetBuffer) throws IOException {
        reason = packetBuffer.readString();
    }

    public String getReason() {
        return reason;
    }
}
