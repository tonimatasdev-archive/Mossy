package net.tonimatasdev.mossy.network.packet.server.status;

import net.tonimatasdev.mossy.network.packet.PacketBuffer;
import net.tonimatasdev.mossy.network.packet.PacketBufferType;
import net.tonimatasdev.mossy.network.packet.server.ServerPacket;

public record PongPacket(long payload) implements ServerPacket {
    public PongPacket(PacketBuffer packetBuffer) {
        this((long) packetBuffer.read(PacketBufferType.LONG));
    }

    @Override
    public void write(PacketBuffer packetBuffer) {
        packetBuffer.write(PacketBufferType.LONG, payload);
    }

    @Override
    public int getId() {
        return 0x01;
    }
}
