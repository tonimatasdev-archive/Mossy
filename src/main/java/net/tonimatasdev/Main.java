package net.tonimatasdev;

import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.Player;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.PlayerBlockBreakEvent;
import net.minestom.server.event.player.PlayerLoginEvent;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;
import net.tonimatasdev.generator.NoiseGenerator;
import net.tonimatasdev.manager.Command;

public class Main {
    public static void main(String[] args) {
        MinecraftServer minecraftServer = MinecraftServer.init();
        InstanceContainer instanceContainer = MinecraftServer.getInstanceManager().createInstanceContainer();

        instanceContainer.setGenerator(new NoiseGenerator());

        GlobalEventHandler globalEventHandler = MinecraftServer.getGlobalEventHandler();

        Command.register();

        globalEventHandler.addListener(PlayerLoginEvent.class, event -> {
            final Player player = event.getPlayer();
            event.setSpawningInstance(instanceContainer);
            player.setRespawnPoint(new Pos(0, 5, 0));

            player.dropItem(ItemStack.of(Material.MOSS_BLOCK, 2));
            player.setGameMode(GameMode.CREATIVE);
        });

        globalEventHandler.addListener(PlayerBlockBreakEvent.class, event -> {

            event.getPlayer().getInventory().addItemStack(ItemStack.of(Material.STONE_SHOVEL));
        });

        minecraftServer.start("0.0.0.0", 25565);
    }
}