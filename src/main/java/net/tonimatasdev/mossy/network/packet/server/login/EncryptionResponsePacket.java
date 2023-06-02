package net.tonimatasdev.mossy.network.packet.server.login;

import net.tonimatasdev.mossy.network.Packet;
import net.tonimatasdev.mossy.network.PacketBuffer;

import java.io.IOException;

public class EncryptionResponsePacket implements Packet {
    private int sharedSecretLength;
    private byte[] sharedSecret;
    private int verifyTokenLength;
    private byte[] verifyToken;

    public EncryptionResponsePacket() {

    }

    public EncryptionResponsePacket(int sharedSecretLength, byte[] sharedSecret, int verifyTokenLength, byte[] verifyToken) {
        this.sharedSecretLength = sharedSecretLength;
        this.sharedSecret = sharedSecret;
        this.verifyTokenLength = verifyTokenLength;
        this.verifyToken = verifyToken;
    }

    public byte[] getSharedSecret() {
        return sharedSecret;
    }

    public byte[] getVerifyToken() {
        return verifyToken;
    }

    @Override
    public void write(PacketBuffer packetBuffer) throws IOException {
        packetBuffer.writeVarInt(sharedSecretLength);
        packetBuffer.writeByteArray(sharedSecret);
        packetBuffer.writeVarInt(verifyTokenLength);
        packetBuffer.writeByteArray(verifyToken);
    }

    @Override
    public void read(PacketBuffer packetBuffer) throws IOException {
        sharedSecretLength = packetBuffer.readVarInt();
        sharedSecret = packetBuffer.readByteArray();
        verifyTokenLength = packetBuffer.readVarInt();
        verifyToken = packetBuffer.readByteArray();
    }
}
