package net.tonimatasdev.mossy.network.packet.client.login;

import net.tonimatasdev.mossy.network.Packet;
import net.tonimatasdev.mossy.network.PacketBuffer;

import java.io.IOException;

public class SetCompressionPacket implements Packet {
    public static int packetId = 0x03;
    private long threshold;

    public SetCompressionPacket(long payload) {
        this.threshold = payload;
    }


    @Override
    public void write(PacketBuffer packetBuffer) throws IOException {
        packetBuffer.writeLong(threshold);
    }

    @Override
    public void read(PacketBuffer packetBuffer) throws IOException {
        threshold = packetBuffer.readLong();
    }

    public long getPayload() {
        return threshold;
    }
}
