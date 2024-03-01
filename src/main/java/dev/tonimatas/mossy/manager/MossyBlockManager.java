package dev.tonimatas.mossy.manager;

import dev.tonimatas.mossy.blocks.AnvilBlockHandler;
import dev.tonimatas.mossy.blocks.ChestBlockHandler;
import dev.tonimatas.mossy.blocks.ChippedAnvilBlockHandler;
import dev.tonimatas.mossy.blocks.DamagedAnvilBlockHandler;
import dev.tonimatas.mossy.logger.Logger;
import net.minestom.server.MinecraftServer;
import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.block.BlockHandler;
import net.minestom.server.instance.block.BlockManager;

import java.util.Objects;

public class MossyBlockManager {
    private static final BlockManager blockManager = MinecraftServer.getBlockManager();

    public static void init() {
        registerHandler(Block.CHEST, new ChestBlockHandler());
        registerHandler(Block.ANVIL, new AnvilBlockHandler());
        registerHandler(Block.DAMAGED_ANVIL, new DamagedAnvilBlockHandler());
        registerHandler(Block.CHIPPED_ANVIL, new ChippedAnvilBlockHandler());
        Logger.info("All block handlers registered.");
    }

    private static void registerHandler(Block block, BlockHandler blockHandler) {
        blockManager.registerHandler(block.namespace(), () -> Objects.requireNonNull(block.withHandler(blockHandler).handler()));
    }
}
