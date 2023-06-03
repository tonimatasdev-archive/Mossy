package net.tonimatasdev.mossy.network.packet.client.status;

import net.tonimatasdev.mossy.network.packet.PacketBuffer;
import net.tonimatasdev.mossy.network.packet.client.ClientPreplayPacket;
import net.tonimatasdev.mossy.network.player.PlayerConnection;

import static net.tonimatasdev.mossy.network.packet.PacketBufferType.LONG;

public record PingPacket(long number) implements ClientPreplayPacket {
    public PingPacket(PacketBuffer packetBuffer) {
        this((long) packetBuffer.read(LONG));
    }

    @Override
    public void process(PlayerConnection connection) {
        // TODO: Make the event
    }

    @Override
    public void write(PacketBuffer writer) {
        writer.write(LONG, number);
    }
}