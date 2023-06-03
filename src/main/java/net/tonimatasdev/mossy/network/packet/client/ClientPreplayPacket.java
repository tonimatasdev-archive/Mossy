package net.tonimatasdev.mossy.network.packet.client;

import net.tonimatasdev.mossy.Mossy;
import net.tonimatasdev.mossy.network.ConnectionManager;
import net.tonimatasdev.mossy.network.player.PlayerConnection;

public interface ClientPreplayPacket extends ClientPacket {
    ConnectionManager CONNECTION_MANAGER = Mossy.getConnectionManager();
    void process(PlayerConnection connection);
}
