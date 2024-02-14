package dev.tonimatas.mossy.events;

import net.minestom.server.MinecraftServer;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.server.ServerTickMonitorEvent;
import net.minestom.server.instance.Instance;

public class ServerEvents {
    public static int tick;

    public static void init(GlobalEventHandler eventHandler) {
        eventHandler.addListener(ServerTickMonitorEvent.class, event -> {
            if (tick >= (5*60*20)) {
                MinecraftServer.getInstanceManager().getInstances().forEach(Instance::saveChunksToStorage);
                tick = 0;
            } else {
                tick++;
            }
        });
    }
}
