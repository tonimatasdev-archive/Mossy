package net.tonimatasdev.mossy;

import net.minestom.server.MinecraftServer;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.extras.MojangAuth;
import net.minestom.server.instance.AnvilLoader;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.InstanceManager;
import net.minestom.server.instance.block.Block;
import net.tonimatasdev.mossy.api.MossyDimensionType;
import net.tonimatasdev.mossy.events.BlockEvents;
import net.tonimatasdev.mossy.events.InstanceEvents;
import net.tonimatasdev.mossy.events.ItemEvents;
import net.tonimatasdev.mossy.events.PlayerEvents;
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

        MossyDimensionType mossyDimensionType = new MossyDimensionType();

        overWorld = instanceManager.createInstanceContainer(mossyDimensionType.getOverworld());
        overWorld.setChunkLoader(new AnvilLoader("world"));
        overWorld.setGenerator(unit -> unit.modifier().fillHeight(0, 40, Block.STONE));
        logger.info("World \"world\" loaded.");

        nether = instanceManager.createInstanceContainer(mossyDimensionType.getNether());
        nether.setChunkLoader(new AnvilLoader("nether"));
        nether.setGenerator(unit -> unit.modifier().fillHeight(0, 40, Block.STONE));
        logger.info("World \"nether\" loaded.");

        end = instanceManager.createInstanceContainer(mossyDimensionType.getEnd());
        end.setGenerator(unit -> unit.modifier().fillHeight(0, 40, Block.STONE));
        end.setChunkLoader(new AnvilLoader("end"));
        logger.info("World \"end\" loaded.");

        GlobalEventHandler eventHandler = MinecraftServer.getGlobalEventHandler();
        BlockEvents.init(eventHandler);
        InstanceEvents.init(eventHandler);
        ItemEvents.init(eventHandler);
        PlayerEvents.init(eventHandler);
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
