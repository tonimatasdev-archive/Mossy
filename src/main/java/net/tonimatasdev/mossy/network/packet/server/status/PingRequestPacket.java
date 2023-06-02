package net.tonimatasdev.mossy.network.packet.server.status;

import net.tonimatasdev.mossy.network.Packet;
import net.tonimatasdev.mossy.network.PacketBuffer;

import java.io.IOException;

public class PingRequestPacket implements Packet {
    public static int packetId = 0x01;
    private long payload;

    public PingRequestPacket(long payload) {
        this.payload = payload;
    }


    @Override
    public void write(PacketBuffer packetBuffer) throws IOException {
        packetBuffer.writeLong(payload);
    }

    @Override
    public void read(PacketBuffer packetBuffer) throws IOException {
        payload = packetBuffer.readLong();
    }

    public long getPayload() {
        return payload;
    }
}
