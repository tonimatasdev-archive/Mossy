package net.tonimatasdev.mossy.network.packet.server.status;

import net.tonimatasdev.mossy.network.Packet;
import net.tonimatasdev.mossy.network.PacketBuffer;

public class StatusRequestPacket implements Packet {
    public static int packetId = 0x01;

    public StatusRequestPacket() {
    }

    @Override
    public void write(PacketBuffer packetBuffer) {

    }

    @Override
    public void read(PacketBuffer packetBuffer) {

    }
}
