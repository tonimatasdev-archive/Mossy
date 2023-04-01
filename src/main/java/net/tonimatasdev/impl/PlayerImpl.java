package net.tonimatasdev.impl;

import net.minestom.server.coordinate.Pos;
import net.minestom.server.coordinate.Vec;
import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.ItemEntity;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.PlayerBlockBreakEvent;
import net.minestom.server.item.ItemStack;

import java.util.Objects;

public class PlayerImpl {
    public static void register(GlobalEventHandler eventHandler) {
        eventHandler.addListener(PlayerBlockBreakEvent.class, event -> {
            if (event.getPlayer().getGameMode() == GameMode.SURVIVAL) {
                ItemEntity item = new ItemEntity(ItemStack.of(Objects.requireNonNull(event.getBlock().registry().material())));
                item.setPickable(true);
                item.setVelocity(new Vec(0, 5.5, 0));
                item.setPose(event.getPlayer().getPose());
                item.setInstance(event.getInstance(), new Pos(event.getBlockPosition().x() + 0.5, event.getBlockPosition().y() + 0.5, event.getBlockPosition().z() + 0.5));
            }
        });
    }
}
