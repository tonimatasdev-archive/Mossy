package net.tonimatasdev.mossy.events;

import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.server.ServerTickMonitorEvent;
import net.tonimatasdev.mossy.Mossy;

public class ServerEvents {
    public static int tick;

    public static void init(GlobalEventHandler eventHandler) {
        eventHandler.addListener(ServerTickMonitorEvent.class, event -> {
            if (tick >= (5*60*20)) {
                Mossy.save();
                tick = 0;
            } else {
                tick++;
            }
        });
    }
}
