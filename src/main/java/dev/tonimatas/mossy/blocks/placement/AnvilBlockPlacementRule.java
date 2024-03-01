package dev.tonimatas.mossy.blocks.placement;

import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.block.rule.BlockPlacementRule;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class AnvilBlockPlacementRule extends BlockPlacementRule {
    private static final String HORIZONTAL_DIRECTION = "vertical_direction";
    
    protected AnvilBlockPlacementRule() {
        super(Block.ANVIL);
    }

    @Override
    public @Nullable Block blockPlace(@NotNull BlockPlacementRule.PlacementState placementState) {
        return null;
    }
}
