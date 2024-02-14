package dev.tonimatas.mossy.api;

import net.minestom.server.utils.NamespaceID;
import net.minestom.server.world.DimensionType;
import net.minestom.server.world.DimensionTypeManager;

public class MossyDimensionType {
    protected DimensionTypeManager dimensionTypeManager;

    public MossyDimensionType() {
        dimensionTypeManager = new DimensionTypeManager();

        dimensionTypeManager.addDimension(DimensionType.builder(NamespaceID.from("minecraft:overworld"))
                .ultrawarm(false)
                .natural(true)
                .piglinSafe(false)
                .respawnAnchorSafe(false)
                .bedSafe(true)
                .raidCapable(true)
                .skylightEnabled(true)
                .ceilingEnabled(false)
                .fixedTime(null)
                .ambientLight(0.0f)
                .height(384)
                .minY(-64)
                .logicalHeight(384)
                .infiniburn(NamespaceID.from("minecraft:infiniburn_overworld"))
                .build());

        dimensionTypeManager.addDimension(DimensionType.builder(NamespaceID.from("minecraft:nether"))
                .ultrawarm(false)
                .natural(true)
                .piglinSafe(false)
                .respawnAnchorSafe(false)
                .bedSafe(true)
                .raidCapable(true)
                .skylightEnabled(true)
                .ceilingEnabled(false)
                .fixedTime(null)
                .ambientLight(0.0f)
                .height(384)
                .minY(-64)
                .logicalHeight(384)
                .infiniburn(NamespaceID.from("minecraft:infiniburn_overworld"))
                .build());

        dimensionTypeManager.addDimension(DimensionType.builder(NamespaceID.from("minecraft:end"))
                .ultrawarm(false)
                .natural(true)
                .piglinSafe(false)
                .respawnAnchorSafe(false)
                .bedSafe(true)
                .raidCapable(true)
                .skylightEnabled(true)
                .ceilingEnabled(false)
                .fixedTime(null)
                .ambientLight(0.0f)
                .height(384)
                .minY(-64)
                .logicalHeight(384)
                .infiniburn(NamespaceID.from("minecraft:infiniburn_overworld"))
                .build());
    }

    public DimensionTypeManager getDimensionTypeManager() {
        return dimensionTypeManager;
    }

    public DimensionType getOverworld() {
        return dimensionTypeManager.getDimension(NamespaceID.from("minecraft:overworld"));
    }

    public DimensionType getNether() {
        return dimensionTypeManager.getDimension(NamespaceID.from("minecraft:nether"));
    }

    public DimensionType getEnd() {
        return dimensionTypeManager.getDimension(NamespaceID.from("minecraft:end"));
    }
}
