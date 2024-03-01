package dev.tonimatas.mossy.events;

import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.inventory.InventoryCloseEvent;
import net.minestom.server.inventory.Inventory;
import net.minestom.server.inventory.InventoryType;
import net.minestom.server.item.ItemStack;

public class InventoryEvents {
    public static void init(GlobalEventHandler eventHandler) {
        eventHandler.addListener(InventoryCloseEvent.class, event -> {
            Inventory inventory = event.getInventory();
            if (inventory == null) return;
            InventoryType inventoryType = inventory.getInventoryType();
            
            if (inventoryType.equals(InventoryType.ANVIL)) {
                for (ItemStack itemStack : inventory.getItemStacks()) {
                    event.getPlayer().getInventory().addItemStack(itemStack);
                }
            }
        });
    }
}
