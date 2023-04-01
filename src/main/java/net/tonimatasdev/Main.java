package net.tonimatasdev;

import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.Player;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.PlayerLoginEvent;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.SharedInstance;
import net.minestom.server.world.DimensionType;
import net.tonimatasdev.generator.NoiseGenerator;
import net.tonimatasdev.impl.BlockImpl;
import net.tonimatasdev.impl.ItemImpl;
import net.tonimatasdev.impl.PlayerImpl;
import net.tonimatasdev.manager.Command;

public class Main {
    public static void main(String[] args) {
        MinecraftServer minecraftServer = MinecraftServer.init();
        InstanceContainer overworld = MinecraftServer.getInstanceManager().createInstanceContainer(DimensionType.OVERWORLD);
        SharedInstance sharedInstance = MinecraftServer.getInstanceManager().createSharedInstance(overworld);

        //instanceContainer.setGenerator(new NoiseGenerator());
        overworld.setGenerator(new NoiseGenerator());

        GlobalEventHandler eventHandler = MinecraftServer.getGlobalEventHandler();
        BlockImpl.register(eventHandler);
        ItemImpl.register(eventHandler);
        PlayerImpl.register(eventHandler);


        Command.register();

        eventHandler.addListener(PlayerLoginEvent.class, event -> {
            final Player player = event.getPlayer();
            event.setSpawningInstance(overworld);
            player.setRespawnPoint(new Pos(0, 100, 0));

            player.setGameMode(GameMode.CREATIVE);
        });

        minecraftServer.start("0.0.0.0", 25565);
    }
}