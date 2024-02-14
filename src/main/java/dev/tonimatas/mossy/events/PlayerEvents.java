package dev.tonimatas.mossy.events;

import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.GameMode;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.AsyncPlayerConfigurationEvent;
import net.minestom.server.instance.block.Block;
import dev.tonimatas.mossy.Mossy;

public class PlayerEvents {
    public static void init(GlobalEventHandler eventHandler) {
        eventHandler.addListener(AsyncPlayerConfigurationEvent.class, event -> {
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
    }
}
