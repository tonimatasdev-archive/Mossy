package net.tonimatasdev.mossy.network.packet.server.login;

import net.tonimatasdev.mossy.network.packet.PacketBuffer;
import net.tonimatasdev.mossy.network.packet.server.ServerPacket;

import static net.tonimatasdev.mossy.network.packet.PacketBufferType.INT;
import static net.tonimatasdev.mossy.network.packet.PacketBufferType.STRING;

public record LoginPluginRequestPacket(int messageId, String channel, byte[] data) implements ServerPacket {
    public LoginPluginRequestPacket(PacketBuffer packetBuffer) {
        this((int) packetBuffer.read(INT), (String) packetBuffer.read(STRING), packetBuffer.readByteArray());
    }

    @Override
    public void write(PacketBuffer packetBuffer) {
        packetBuffer.write(INT, messageId);
        packetBuffer.write(STRING, channel);
        if (data != null && data.length > 0) {
            packetBuffer.writeByteArray(data);
        }
    }

    @Override
    public int getId() {
        return 0x04;
    }
}
