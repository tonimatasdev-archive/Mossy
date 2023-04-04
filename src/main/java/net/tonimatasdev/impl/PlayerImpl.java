package net.tonimatasdev.impl;

import net.minestom.server.coordinate.Pos;
import net.minestom.server.coordinate.Vec;
import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.ItemEntity;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.PlayerBlockBreakEvent;
import net.minestom.server.event.player.PlayerLoginEvent;
import net.minestom.server.instance.block.Block;
import net.minestom.server.item.ItemStack;
import net.tonimatasdev.Main;

import java.util.Objects;
import java.util.Random;

public class PlayerImpl {
    @SuppressWarnings("UnstableApiUsage")
    public static void register(GlobalEventHandler eventHandler) {
        eventHandler.addListener(PlayerLoginEvent.class, event -> {
            event.setSpawningInstance(Main.overWorld);

            event.getPlayer().setGameMode(GameMode.SURVIVAL);
            Main.overWorld.loadChunk(0, 0).join();

            int y = Main.overWorld.getDimensionType().getMaxY();

            while (Block.AIR.compare(Main.overWorld.getBlock(0, y, 0))) {
                y--;
                if (y == Main.overWorld.getDimensionType().getMinY()) {
                    break;
                }
            }
            event.getPlayer().setRespawnPoint(new Pos(0, y, 0));
        });

        eventHandler.addListener(PlayerBlockBreakEvent.class, event -> {
            if (event.getPlayer().getGameMode() == GameMode.SURVIVAL) {
                ItemEntity item = new ItemEntity(ItemStack.of(Objects.requireNonNull(event.getBlock().registry().material())));
                item.setPickable(true);
                Random random = new Random();
                item.setVelocity(new Vec(random.nextDouble() * (2.5 - (-2.5)) + (-2.5), 3.9, random.nextDouble() * (2.5 - (-2.5)) + (-2.5)));
                item.setPose(event.getPlayer().getPose());
                item.setInstance(event.getInstance(), new Pos(event.getBlockPosition().x() + 0.5, event.getBlockPosition().y() + 0.5, event.getBlockPosition().z() + 0.5));
            }
        });
    }
}
