package net.tonimatasdev.mossy;

import net.minestom.server.MinecraftServer;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.world.DimensionType;
import net.tonimatasdev.mossy.generator.NoiseGenerator;
import net.tonimatasdev.mossy.impl.BlockImpl;
import net.tonimatasdev.mossy.impl.ItemImpl;
import net.tonimatasdev.mossy.impl.PlayerImpl;
import net.tonimatasdev.mossy.manager.Command;

import java.util.logging.Logger;

public class Mossy {
    public static Logger logger = Logger.getLogger("Mossy");
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