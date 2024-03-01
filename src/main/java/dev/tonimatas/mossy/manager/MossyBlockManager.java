package dev.tonimatas.mossy.manager;

import dev.tonimatas.mossy.blocks.ChestBlockHandler;
import net.minestom.server.MinecraftServer;
import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.block.BlockHandler;
import net.minestom.server.instance.block.BlockManager;
import net.minestom.server.utils.NamespaceID;
import dev.tonimatas.mossy.logger.Logger;

import java.util.Objects;

public class MossyBlockManager {
    private static final BlockManager blockManager = MinecraftServer.getBlockManager();

    public static void init() {
        registerHandler(Block.CHEST, new ChestBlockHandler());
        Logger.info("All block handlers registered.");
    }

    private static void registerHandler(Block block, BlockHandler blockHandler) {
        blockManager.registerHandler(NamespaceID.from("minecraft:" + block.name().toLowerCase()), () -> Objects.requireNonNull(block.withHandler(blockHandler).handler()));
    }
}
