package dev.tonimatas.mossy.blocks;

import net.kyori.adventure.text.Component;
import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.block.BlockHandler;
import net.minestom.server.inventory.Inventory;
import net.minestom.server.inventory.InventoryType;
import net.minestom.server.inventory.type.AnvilInventory;
import net.minestom.server.utils.NamespaceID;
import org.jetbrains.annotations.NotNull;

public class AnvilBlockHandler implements BlockHandler {
    @Override
    public boolean onInteract(@NotNull BlockHandler.Interaction interaction) {
        interaction.getPlayer().openInventory(new Inventory(InventoryType.ANVIL, Component.translatable("container.repair")));
        
        // TODO: Add damage phases
        return true;
    }

    @Override
    public @NotNull NamespaceID getNamespaceId() {
        return Block.ANVIL.namespace();
    }
}
