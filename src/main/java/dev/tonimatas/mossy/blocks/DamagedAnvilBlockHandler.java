package dev.tonimatas.mossy.blocks;

import net.minestom.server.instance.block.Block;
import net.minestom.server.utils.NamespaceID;
import org.jetbrains.annotations.NotNull;

public class DamagedAnvilBlockHandler extends AnvilBlockHandler {
    @Override
    public @NotNull NamespaceID getNamespaceId() {
        return Block.DAMAGED_ANVIL.namespace();
    }
}
