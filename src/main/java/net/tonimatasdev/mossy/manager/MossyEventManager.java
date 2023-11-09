package net.tonimatasdev.mossy.manager;

import net.minestom.server.MinecraftServer;
import net.minestom.server.event.GlobalEventHandler;
import net.tonimatasdev.mossy.events.*;
import net.tonimatasdev.mossy.logger.Logger;

public class MossyEventManager {
    private static final GlobalEventHandler eventHandler = MinecraftServer.getGlobalEventHandler();

    public static void init() {
        BlockEvents.init(eventHandler);
        InstanceEvents.init(eventHandler);
        ItemEvents.init(eventHandler);
        PlayerEvents.init(eventHandler);
        ServerEvents.init(eventHandler);
        Logger.info("All events registered.");
    }
}
