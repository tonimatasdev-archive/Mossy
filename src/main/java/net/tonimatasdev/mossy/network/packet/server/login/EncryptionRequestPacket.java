package net.tonimatasdev.mossy.network.packet.server.login;

import net.tonimatasdev.mossy.network.packet.PacketBuffer;
import net.tonimatasdev.mossy.network.packet.server.ServerPacket;

import static net.tonimatasdev.mossy.network.packet.PacketBufferType.STRING;

public record EncryptionRequestPacket(String serverId, byte[] publicKey, byte[] verifyToken) implements ServerPacket {
    public EncryptionRequestPacket(PacketBuffer packetBuffer) {
        this((String) packetBuffer.read(STRING), packetBuffer.readByteArray(), packetBuffer.readByteArray());
    }

    @Override
    public void write(PacketBuffer packetBuffer) {
        packetBuffer.write(STRING, serverId);
        packetBuffer.writeByteArray(publicKey);
        packetBuffer.writeByteArray(verifyToken);
    }

    @Override
    public int getId() {
        return 0x01;
    }
}
