package net.tonimatasdev.mossy.network;

import java.io.IOException;

public interface Packet {
    void write(PacketBuffer packetBuffer) throws IOException;
    void read(PacketBuffer packetBuffer) throws IOException;
}