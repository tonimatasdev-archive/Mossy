package net.tonimatasdev.mossy.network.packet.server.handshake;

import net.tonimatasdev.mossy.network.packet.PacketBuffer;
import net.tonimatasdev.mossy.network.packet.server.ServerPacket;

import static net.tonimatasdev.mossy.network.packet.PacketBufferType.STRING;

public record ResponsePacket(String jsonResponse) implements ServerPacket {
    public ResponsePacket(PacketBuffer packetBuffer) {
        this((String) packetBuffer.read(STRING));
    }

    @Override
    public void write(PacketBuffer packetBuffer) {
        packetBuffer.write(STRING, jsonResponse);
    }

    @Override
    public int getId() {
        return 0x00;
    }
}
