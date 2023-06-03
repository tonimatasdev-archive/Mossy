package net.tonimatasdev.mossy.network.packet.server.login;

import net.tonimatasdev.mossy.network.packet.PacketBuffer;
import net.tonimatasdev.mossy.network.packet.server.ServerPacket;

import static net.tonimatasdev.mossy.network.packet.PacketBufferType.INT;

public record SetCompressionPacket(int threshold) implements ServerPacket {
    public SetCompressionPacket(PacketBuffer packetBuffer) {
        this((int) packetBuffer.read(INT));
    }

    @Override
    public void write(PacketBuffer packetBuffer) {
        packetBuffer.write(INT, threshold);
    }

    @Override
    public int getId() {
        return 0x03;
    }
}
