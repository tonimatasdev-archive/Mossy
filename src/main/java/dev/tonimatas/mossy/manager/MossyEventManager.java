package dev.tonimatas.mossy.manager;

import dev.tonimatas.mossy.events.*;
import net.minestom.server.MinecraftServer;
import net.minestom.server.event.GlobalEventHandler;
import dev.tonimatas.mossy.logger.Logger;

public class MossyEventManager {
    public static void init() {
        GlobalEventHandler eventHandler = MinecraftServer.getGlobalEventHandler();
        BlockEvents.init(eventHandler);
        InstanceEvents.init(eventHandler);
        InventoryEvents.init(eventHandler);
        ItemEvents.init(eventHandler);
        PlayerEvents.init(eventHandler);
        ServerEvents.init(eventHandler);
        Logger.info("All events registered.");
    }
}
