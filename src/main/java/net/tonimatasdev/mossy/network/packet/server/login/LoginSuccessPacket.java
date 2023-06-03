package net.tonimatasdev.mossy.network.packet.server.login;

import net.tonimatasdev.mossy.network.packet.PacketBuffer;
import net.tonimatasdev.mossy.network.packet.server.ServerPacket;

import static net.tonimatasdev.mossy.network.packet.PacketBufferType.INT;
import static net.tonimatasdev.mossy.network.packet.PacketBufferType.STRING;

public record LoginSuccessPacket(String uuid, String username, int properties) implements ServerPacket {
    public LoginSuccessPacket(PacketBuffer packetBuffer) {
        this((String) packetBuffer.read(STRING), (String) packetBuffer.read(STRING), (Integer) packetBuffer.read(INT));
    }

    @Override
    public void write(PacketBuffer packetBuffer) {
        packetBuffer.write(STRING, uuid);
        packetBuffer.write(STRING, username);
        packetBuffer.write(INT, properties);
    }

    @Override
    public int getId() {
        return 0x02;
    }
}
