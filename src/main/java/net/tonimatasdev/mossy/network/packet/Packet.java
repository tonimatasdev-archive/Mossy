package net.tonimatasdev.mossy.network.packet;

public interface Packet {
    void write(PacketBuffer packetBuffer);
}