package net.tonimatasdev.mossy.events;

import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.instance.InstanceTickEvent;
import net.tonimatasdev.mossy.Mossy;

public class InstanceEvents {
    public static int tick;

    public static void init(GlobalEventHandler eventHandler) {
        eventHandler.addListener(InstanceTickEvent.class, event -> {
            if (tick >= (5*60*20)) {
                Mossy.save();
                System.out.println("Auto saved.");
                tick = 0;
            } else {
                tick++;
            }
        });
    }
}
