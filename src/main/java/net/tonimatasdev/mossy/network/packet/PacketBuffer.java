package net.tonimatasdev.mossy.network.packet;

import java.io.*;

public class PacketBuffer {
    private final DataInputStream dataInputStream;
    private final DataOutputStream dataOutputStream;

    public PacketBuffer(InputStream inputStream, OutputStream outputStream) {
        this.dataInputStream = new DataInputStream(inputStream);
        this.dataOutputStream = new DataOutputStream(outputStream);
    }

    public void write(PacketBufferType type, Object value) {
        try {
            switch (type) {
                case INT -> dataOutputStream.writeInt((int) value);
                case LONG -> dataOutputStream.writeLong((long) value);
                case BOOLEAN -> dataOutputStream.writeBoolean((boolean) value);
                case BYTE -> dataOutputStream.writeByte((byte) value);
                case SHORT -> dataOutputStream.writeShort((short) value);
                case STRING -> dataOutputStream.writeUTF((String) value);
                default -> System.out.println(type.name() + " is impossible write this type.");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Object read(PacketBufferType type) {
        try {
            return switch (type) {
                case INT -> dataInputStream.readInt();
                case LONG -> dataInputStream.readLong();
                case BOOLEAN -> dataInputStream.readBoolean();
                case BYTE -> dataInputStream.readByte();
                case SHORT -> dataInputStream.readShort();
                case STRING -> dataInputStream.readUTF();
                case UNSIGNED_SHORT -> dataInputStream.readUnsignedShort();
                case UNSIGNED_BYTE -> dataInputStream.readUnsignedByte();
            };
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeByteArray(byte[] value){
        try {
            dataOutputStream.writeShort(value.length);
            dataOutputStream.write(value);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] readByteArray(){
        int length = 0;
        try {
            length = dataInputStream.readShort();
            byte[] value = new byte[length];
            dataInputStream.readFully(value);
            return value;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}