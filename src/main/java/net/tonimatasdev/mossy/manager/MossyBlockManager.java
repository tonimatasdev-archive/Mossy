package net.tonimatasdev.mossy.manager;

import net.minestom.server.MinecraftServer;
import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.block.BlockHandler;
import net.minestom.server.instance.block.BlockManager;
import net.minestom.server.utils.NamespaceID;
import net.tonimatasdev.mossy.api.blocks.ChestBlockHandler;
import net.tonimatasdev.mossy.logger.Logger;

import java.util.Objects;

public class MossyBlockManager {
    private static BlockManager blockManager = MinecraftServer.getBlockManager();

    public static void init() {
        registerHandler(Block.CHEST, new ChestBlockHandler());
        Logger.info("All blocks registered.");
    }

    private static void registerHandler(Block block, BlockHandler blockHandler) {
        blockManager.registerHandler(NamespaceID.from("minecraft:" + block.name().toLowerCase()), () -> Objects.requireNonNull(block.withHandler(new ChestBlockHandler()).handler()));
    }
}
