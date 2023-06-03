package net.tonimatasdev.mossy;

import net.minestom.server.MinecraftServer;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.world.DimensionType;
import net.tonimatasdev.mossy.api.World;
import net.tonimatasdev.mossy.generator.NoiseGenerator;
import net.tonimatasdev.mossy.impl.BlockImpl;
import net.tonimatasdev.mossy.impl.ItemImpl;
import net.tonimatasdev.mossy.impl.PlayerImpl;
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

        GlobalEventHandler eventHandler = MinecraftServer.getGlobalEventHandler();
        BlockImpl.register(eventHandler);
        ItemImpl.register(eventHandler);
        PlayerImpl.register(eventHandler);

        Command.register();

        minecraftServer.start("0.0.0.0", 25565);
    }
}
