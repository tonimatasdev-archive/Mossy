package net.tonimatasdev.mossy.events;

import net.minestom.server.coordinate.Pos;
import net.minestom.server.coordinate.Vec;
import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.ItemEntity;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.PlayerBlockBreakEvent;
import net.minestom.server.event.player.PlayerLoginEvent;
import net.minestom.server.instance.LightingChunk;
import net.minestom.server.instance.block.Block;
import net.minestom.server.item.ItemStack;
import net.tonimatasdev.mossy.Mossy;

import java.util.Collections;
import java.util.Objects;
import java.util.Random;

public class PlayerEvents {
    @SuppressWarnings("UnstableApiUsage")
    public static void register(GlobalEventHandler eventHandler) {
        eventHandler.addListener(PlayerLoginEvent.class, event -> {
            event.setSpawningInstance(Mossy.overWorld);

            event.getPlayer().setGameMode(GameMode.SURVIVAL);
            Mossy.overWorld.loadChunk(0, 0).join();

            int y = Mossy.overWorld.getDimensionType().getMaxY();

            while (Block.AIR.compare(Mossy.overWorld.getBlock(0, y, 0))) {
                y--;
                if (y == Mossy.overWorld.getDimensionType().getMinY()) {
                    break;
                }
            }
            event.getPlayer().setRespawnPoint(new Pos(0, y, 0));
        });

        eventHandler.addListener(PlayerBlockBreakEvent.class, event -> {
            if (event.getPlayer().getGameMode() == GameMode.SURVIVAL) {
                LightingChunk.relight(event.getInstance(), Collections.singleton(event.getPlayer().getChunk()));
                ItemEntity item = new ItemEntity(ItemStack.of(Objects.requireNonNull(event.getBlock().registry().material())));
                item.setPickable(true);
                item.setMergeable(true);
                item.setMergeRange(1.6f);
                Random random = new Random();
                item.setVelocity(new Vec(random.nextDouble() * (2.2 - (-2.2)) + (-2.2), 3.6, random.nextDouble() * (2.2 - (-2.2)) + (-2.2)));
                item.setPose(event.getPlayer().getPose());
                item.setInstance(event.getInstance(), new Pos(event.getBlockPosition().x() + 0.5, event.getBlockPosition().y() + 0.5, event.getBlockPosition().z() + 0.5));
            }
        });
    }
}
