package net.tonimatasdev.mossy.api;

import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.InstanceManager;
import net.minestom.server.world.DimensionType;

import java.util.HashMap;
import java.util.Map;

public class World {
    private static final Map<String, World> worlds = new HashMap<>();
    private final InstanceContainer instanceContainer;
    private final String name;

    public World(String name, DimensionType dimensionType) {
        instanceContainer = new InstanceManager().createInstanceContainer(dimensionType);
        this.name = name;
        worlds.put(name, this);
    }

    public InstanceContainer getInstanceContainer() {
        return instanceContainer;
    }

    public String getName() {
        return name;
    }

    public static Map<String, World> getWorlds() {
        return worlds;
    }
}
