package net.tonimatasdev.mossy.network;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class PacketBuffer {
    private final DataInputStream dataInputStream;
    private final DataOutputStream dataOutputStream;

    public PacketBuffer(InputStream inputStream, OutputStream outputStream) {
        this.dataInputStream = new DataInputStream(inputStream);
        this.dataOutputStream = new DataOutputStream(outputStream);
    }

    public void writeVarInt(int value) throws IOException {
        dataOutputStream.writeInt(value);
    }

    public int readVarInt() throws IOException {
        return dataInputStream.readInt();
    }

    public void writeByte(byte value) throws IOException {
        dataOutputStream.writeByte(value);
    }

    public byte readByte() throws IOException {
        return dataInputStream.readByte();
    }

    public int readUnsignedByte() throws IOException {
        return dataInputStream.readUnsignedByte();
    }

    public void writeShort(short value) throws IOException {
        dataOutputStream.writeShort(value);
    }

    public short readShort() throws IOException {
        return dataInputStream.readShort();
    }

    public int readUnsignedShort() throws IOException {
        return dataInputStream.readUnsignedShort();
    }

    public void writeByteArray(byte[] value) throws IOException {
        dataOutputStream.writeShort(value.length);
        dataOutputStream.write(value);
    }

    public byte[] readByteArray() throws IOException {
        int length = dataInputStream.readShort();
        byte[] value = new byte[length];
        dataInputStream.readFully(value);
        return value;
    }

    public void writeBoolean(boolean value) throws IOException {
        dataOutputStream.writeBoolean(value);
    }

    public boolean readBoolean() throws IOException {
        return dataInputStream.readBoolean();
    }

    public void writeString(String value) throws IOException {
        byte[] bytes = value.getBytes(StandardCharsets.UTF_8);
        writeVarInt(bytes.length);
        dataOutputStream.write(bytes);
    }

    public String readString() throws IOException {
        int length = readVarInt();
        byte[] bytes = new byte[length];
        dataInputStream.readFully(bytes);
        return new String(bytes, StandardCharsets.UTF_8);
    }

    public void writeLong(long value) throws IOException {
        dataOutputStream.writeLong(value);
    }

    public long readLong() throws IOException {
        return dataInputStream.readLong();
    }

    public byte[] toByteArray() throws IOException {
        dataOutputStream.flush();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        return byteArrayOutputStream.toByteArray();
    }
}