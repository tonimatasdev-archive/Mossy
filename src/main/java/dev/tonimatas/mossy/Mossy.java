package dev.tonimatas.mossy;

import dev.tonimatas.mossy.api.MossyDimensionType;
import dev.tonimatas.mossy.util.ConsoleThread;
import net.minestom.server.MinecraftServer;
import net.minestom.server.extras.MojangAuth;
import net.minestom.server.instance.AnvilLoader;
import net.minestom.server.instance.Instance;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.InstanceManager;
import net.minestom.server.instance.block.Block;
import dev.tonimatas.mossy.logger.Logger;
import dev.tonimatas.mossy.manager.MossyBlockManager;
import dev.tonimatas.mossy.manager.MossyCommandManager;
import dev.tonimatas.mossy.manager.MossyEventManager;

public class Mossy {
    public static InstanceContainer overWorld;
    public static InstanceContainer nether;
    public static InstanceContainer end;

    public static void main(String[] args) {
        long time = System.currentTimeMillis();

        MinecraftServer minecraftServer = MinecraftServer.init();
        InstanceManager instanceManager = MinecraftServer.getInstanceManager();
        MossyDimensionType mossyDimensionType = new MossyDimensionType();

        overWorld = instanceManager.createInstanceContainer(mossyDimensionType.getOverworld());
        overWorld.setChunkLoader(new AnvilLoader("world"));
        overWorld.setGenerator(unit -> unit.modifier().fillHeight(0, 40, Block.STONE));
        Logger.info("World \"world\" loaded.");

        nether = instanceManager.createInstanceContainer(mossyDimensionType.getNether());
        nether.setChunkLoader(new AnvilLoader("nether"));
        nether.setGenerator(unit -> unit.modifier().fillHeight(0, 40, Block.STONE));
        Logger.info("World \"nether\" loaded.");

        end = instanceManager.createInstanceContainer(mossyDimensionType.getEnd());
        end.setGenerator(unit -> unit.modifier().fillHeight(0, 40, Block.STONE));
        end.setChunkLoader(new AnvilLoader("end"));
        Logger.info("World \"end\" loaded.");

        MossyBlockManager.init();
        MossyCommandManager.init();
        MossyEventManager.init();

        MojangAuth.init(); // Premium

        minecraftServer.start("0.0.0.0", 25565);
        new ConsoleThread().start();
        Logger.info("Done! (" + (System.currentTimeMillis() - time) + "ms)");
    }

    public static void stop() {
        long time = System.currentTimeMillis();
        ConsoleThread.stopConsole();
        MinecraftServer.getInstanceManager().getInstances().forEach(Instance::saveChunksToStorage);
        MinecraftServer.stopCleanly();
        Logger.info("Finish! (" + (System.currentTimeMillis() - time) + "ms)");
    }
}
