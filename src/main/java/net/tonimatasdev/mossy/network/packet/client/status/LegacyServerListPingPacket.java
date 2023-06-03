package net.tonimatasdev.mossy.network.packet.client.status;

import net.tonimatasdev.mossy.network.packet.PacketBuffer;
import net.tonimatasdev.mossy.network.packet.client.ClientPreplayPacket;
import net.tonimatasdev.mossy.network.player.PlayerConnection;

import static net.tonimatasdev.mossy.network.packet.PacketBufferType.BYTE;

public record LegacyServerListPingPacket(byte payload) implements ClientPreplayPacket {
    public LegacyServerListPingPacket(PacketBuffer reader) {
        this((byte) reader.read(BYTE));
    }

    @Override
    public void process(PlayerConnection connection) {

    }

    @Override
    public void write(PacketBuffer packetBuffer) {
        packetBuffer.write(BYTE, payload);
    }
}
