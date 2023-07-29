package net.tonimatasdev.mossy;

import net.minestom.server.MinecraftServer;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.extras.MojangAuth;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.world.DimensionType;
import net.tonimatasdev.mossy.api.World;
import net.tonimatasdev.mossy.generator.NoiseGenerator;
import net.tonimatasdev.mossy.events.BlockEvents;
import net.tonimatasdev.mossy.events.ItemEvents;
import net.tonimatasdev.mossy.events.PlayerEvents;
import net.tonimatasdev.mossy.manager.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Mossy {
    public static Logger logger = LoggerFactory.getLogger(Mossy.class);
    public static InstanceContainer overWorld;

    public static void main(String[] args) {
        MinecraftServer minecraftServer = MinecraftServer.init();

        World world = new World("world", DimensionType.OVERWORLD);
        overWorld = world.getInstanceContainer();
        world.getInstanceContainer().setGenerator(new NoiseGenerator());
        logger.info("World loaded.");

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
}
