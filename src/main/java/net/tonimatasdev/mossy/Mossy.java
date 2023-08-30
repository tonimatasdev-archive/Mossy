package net.tonimatasdev.mossy;

import net.minestom.server.MinecraftServer;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.extras.MojangAuth;
import net.minestom.server.instance.AnvilLoader;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.InstanceManager;
import net.tonimatasdev.mossy.api.MossyDimensionType;
import net.tonimatasdev.mossy.events.BlockEvents;
import net.tonimatasdev.mossy.events.ItemEvents;
import net.tonimatasdev.mossy.events.PlayerEvents;
import net.tonimatasdev.mossy.generator.NoiseGenerator;
import net.tonimatasdev.mossy.manager.Command;

import java.util.logging.Logger;

public class Mossy {
    public static Logger logger = Logger.getLogger("Reforged");
    public static InstanceContainer overWorld;
    public static InstanceContainer nether;
    public static InstanceContainer end;

    public static void main(String[] args) {
        MinecraftServer minecraftServer = MinecraftServer.init();

        InstanceManager instanceManager = MinecraftServer.getInstanceManager();

        overWorld = instanceManager.createInstanceContainer(MossyDimensionType.OVERWORLD);
        overWorld.setChunkLoader(new AnvilLoader("world"));
        overWorld.setGenerator(new NoiseGenerator());
        logger.info("World \"world\" loaded.");

        nether = instanceManager.createInstanceContainer(MossyDimensionType.NETHER);
        nether.setChunkLoader(new AnvilLoader("nether"));
        nether.setGenerator(new NoiseGenerator());
        logger.info("World \"nether\" loaded.");

        end = instanceManager.createInstanceContainer(MossyDimensionType.END);
        end.setChunkLoader(new AnvilLoader("end"));
        end.setGenerator(new NoiseGenerator());
        logger.info("World \"end\" loaded.");

        GlobalEventHandler eventHandler = MinecraftServer.getGlobalEventHandler();
        BlockEvents.register(eventHandler);
        ItemEvents.register(eventHandler);
        PlayerEvents.register(eventHandler);
        logger.info("All events registered.");

        Command.register();
        logger.info("All commands registered.");

        MojangAuth.init(); // Premium

        minecraftServer.start("0.0.0.0", 25565);
    }

    public static void save() {
        overWorld.saveChunksToStorage();
        nether.saveChunksToStorage();
        end.saveChunksToStorage();
    }
}
