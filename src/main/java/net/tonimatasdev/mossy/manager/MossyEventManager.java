package net.tonimatasdev.mossy.manager;

import net.minestom.server.MinecraftServer;
import net.minestom.server.event.GlobalEventHandler;
import net.tonimatasdev.mossy.events.*;
import net.tonimatasdev.mossy.logger.Logger;

public class MossyEventManager {
    public static void init() {
        GlobalEventHandler eventHandler = MinecraftServer.getGlobalEventHandler();
        BlockEvents.init(eventHandler);
        InstanceEvents.init(eventHandler);
        ItemEvents.init(eventHandler);
        PlayerEvents.init(eventHandler);
        ServerEvents.init(eventHandler);
        Logger.info("All events registered.");
    }
}
