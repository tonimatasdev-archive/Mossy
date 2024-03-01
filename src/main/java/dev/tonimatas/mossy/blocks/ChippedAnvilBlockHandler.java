package dev.tonimatas.mossy.blocks;

import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.block.BlockHandler;
import net.minestom.server.inventory.type.AnvilInventory;
import net.minestom.server.utils.NamespaceID;
import org.jetbrains.annotations.NotNull;

public class ChippedAnvilBlockHandler extends AnvilBlockHandler {
    @Override
    public @NotNull NamespaceID getNamespaceId() {
        return Block.CHIPPED_ANVIL.namespace();
    }
}
