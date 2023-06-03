package net.tonimatasdev.mossy.network.packet.client.status;

import net.tonimatasdev.mossy.network.packet.PacketBuffer;
import net.tonimatasdev.mossy.network.packet.client.ClientPreplayPacket;
import net.tonimatasdev.mossy.network.player.PlayerConnection;

public record StatusRequestPacket() implements ClientPreplayPacket {
    public StatusRequestPacket(PacketBuffer packetBuffer) {
        this();
    }

    @Override
    public void process(PlayerConnection connection) {
        // TODO: Make the event
    }

    @Override
    public void write(PacketBuffer writer) {
        // Empty
    }
}