package net.tonimatasdev;

import net.minestom.server.MinecraftServer;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.world.DimensionType;
import net.tonimatasdev.generator.NoiseGenerator;
import net.tonimatasdev.impl.BlockImpl;
import net.tonimatasdev.impl.ItemImpl;
import net.tonimatasdev.impl.PlayerImpl;
import net.tonimatasdev.manager.Command;

public class Main {
    public static InstanceContainer overWorld;

    public static void main(String[] args) {
        MinecraftServer minecraftServer = MinecraftServer.init();

        overWorld = MinecraftServer.getInstanceManager().createInstanceContainer(DimensionType.OVERWORLD);

        overWorld.setGenerator(new NoiseGenerator());

        GlobalEventHandler eventHandler = MinecraftServer.getGlobalEventHandler();
        BlockImpl.register(eventHandler);
        ItemImpl.register(eventHandler);
        PlayerImpl.register(eventHandler);

        Command.register();

        minecraftServer.start("0.0.0.0", 25565);
    }
}