package net.tonimatasdev.init;

import net.minestom.server.coordinate.Pos;
import net.minestom.server.coordinate.Vec;
import net.minestom.server.entity.ItemEntity;
import net.minestom.server.entity.Player;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.item.ItemDropEvent;
import net.minestom.server.event.item.PickupItemEvent;

import java.time.Duration;

public class ItemInit {
    public static void register(GlobalEventHandler eventHandler) {
        eventHandler.addListener(PickupItemEvent .class, event -> {
            if (event.getEntity() instanceof Player player) {
                player.getInventory().addItemStack(event.getItemStack());
            }
        });

        eventHandler.addListener(ItemDropEvent.class, event -> {
            ItemEntity item = new ItemEntity(event.getItemStack());
            Pos pos = event.getPlayer().getPosition();

            item.setPickable(true);
            item.setPickupDelay(Duration.ofMillis(2500));
            item.setInstance(event.getInstance(), new Pos(pos.x() + 0.5, pos.y() + 0.5, pos.z() + 0.5));
            item.setVelocity(new Vec(0, 1, 0));
        });
    }
}
