package net.tonimatasdev.mossy.network.packet.server;

import net.tonimatasdev.mossy.network.packet.Packet;

public interface ServerPacket extends Packet {
    int getId();
}
