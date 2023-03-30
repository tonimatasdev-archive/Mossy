package net.tonimatasdev;

import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.coordinate.Vec;
import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.ItemEntity;
import net.minestom.server.entity.Player;
import net.minestom.server.entity.PlayerSkin;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.PlayerBlockBreakEvent;
import net.minestom.server.event.player.PlayerLoginEvent;
import net.minestom.server.event.player.PlayerSkinInitEvent;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.SharedInstance;
import net.minestom.server.instance.block.Block;
import net.minestom.server.item.ItemStack;
import net.minestom.server.world.DimensionType;
import net.tonimatasdev.manager.Command;

import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        MinecraftServer minecraftServer = MinecraftServer.init();
        InstanceContainer overworld = MinecraftServer.getInstanceManager().createInstanceContainer(DimensionType.OVERWORLD);
        SharedInstance sharedInstance = MinecraftServer.getInstanceManager().createSharedInstance(overworld);

        //instanceContainer.setGenerator(new NoiseGenerator());
        overworld.setGenerator(unit -> {
            unit.modifier().fillHeight(-64, -63, Block.BEDROCK);
            unit.modifier().fillHeight(-63, 62, Block.STONE);
            unit.modifier().fillHeight(62, 63, Block.GRASS_BLOCK);
        });

        GlobalEventHandler globalEventHandler = MinecraftServer.getGlobalEventHandler();

        Command.register();

        globalEventHandler.addListener(PlayerLoginEvent.class, event -> {
            final Player player = event.getPlayer();
            event.setSpawningInstance(overworld);
            player.setRespawnPoint(new Pos(0, 64, 0));

            player.setGameMode(GameMode.CREATIVE);
        });

        globalEventHandler.addListener(PlayerSkinInitEvent.class, event -> {
            PlayerSkin skin = PlayerSkin.fromUuid(event.getPlayer().getUuid().toString());
            event.setSkin(skin);
        });

        globalEventHandler.addListener(PlayerBlockBreakEvent.class, event -> {
            ItemEntity item = new ItemEntity(ItemStack.of(Objects.requireNonNull(event.getBlock().registry().material())));
            item.setPickable(true);
            item.setVelocity(new Vec(0, 5.5, 0));
            item.setInstance(event.getInstance(), new Pos(event.getBlockPosition().x() + 0.5, event.getBlockPosition().y() + 0.5, event.getBlockPosition().z() + 0.5));

        });

        minecraftServer.start("0.0.0.0", 25565);
    }
}