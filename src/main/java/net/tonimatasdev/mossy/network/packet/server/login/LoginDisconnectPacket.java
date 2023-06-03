package net.tonimatasdev.mossy.network.packet.server.login;


import net.tonimatasdev.mossy.network.packet.PacketBuffer;
import net.tonimatasdev.mossy.network.packet.server.ServerPacket;

import static net.tonimatasdev.mossy.network.packet.PacketBufferType.STRING;

public record LoginDisconnectPacket(String kickMessage) implements ServerPacket {
    public LoginDisconnectPacket(PacketBuffer packetBuffer) {
        this((String) packetBuffer.read(STRING));
    }

    @Override
    public void write(PacketBuffer packetBuffer) {
        packetBuffer.write(STRING, kickMessage);
    }

    @Override
    public int getId() {
        return 0x00;
    }

    @Override
    public String kickMessage() {
        return kickMessage;
    }
}
