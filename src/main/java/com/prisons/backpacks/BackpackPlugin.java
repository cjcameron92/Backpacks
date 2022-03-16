package com.prisons.backpacks;

import com.prisons.backpacks.backpack.Backpack;
import com.prisons.backpacks.backpack.BackpackFactory;
import com.prisons.backpacks.backpack.BackpackProps;
import com.prisons.backpacks.backpack.SimpleBackpackFactory;
import me.lucko.helper.Events;
import me.lucko.helper.plugin.ExtendedJavaPlugin;
import me.lucko.helper.terminable.TerminableConsumer;
import me.lucko.helper.terminable.module.TerminableModule;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class BackpackPlugin extends ExtendedJavaPlugin implements TerminableModule {

    private final Map<UUID, Integer> backpackSlotCache = new HashMap<>();
    private BackpackProps backpackProps;

    @Override protected void enable() {
        provideService(BackpackFactory.class, new SimpleBackpackFactory());

        backpackProps = new BackpackProps(loadConfig("config.yml"));
    }

    @Override protected void disable() {

    }

    @Override public void setup(@NotNull TerminableConsumer consumer) {
        Events.subscribe(BlockBreakEvent.class).handler(event -> {
            Player player = event.getPlayer();
            Block block = event.getBlock();

            if (backpackProps.getBlacklistedWorlds().stream().anyMatch(world -> world.equalsIgnoreCase(block.getWorld().getName()))) return;
            if (backpackProps.getBlacklistedTypes().stream().anyMatch(type -> block.getType() == type)) return;

            PlayerInventory inventory = player.getInventory();
            Material type = block.getType();

            if (backpackSlotCache.containsKey(player.getUniqueId())) {
                int slot = backpackSlotCache.get(player.getUniqueId());
                if (computeBackpack(getBackpackFromSlot(inventory, slot), type)) {
                    return;
                }
            }

            for (int slot = 0; slot < 36; slot++) {
                if (computeBackpack(getBackpackFromSlot(inventory, slot), type)) {
                    backpackSlotCache.put(player.getUniqueId(), slot);
                    break;
                }
            }

        }).bindWith(consumer);
    }

    /**
     * Handle computation of the backpack
     *
     * @param optionalBackpack - backpack
     * @param type - material
     * @return true/false
     */
    public boolean computeBackpack(Optional<Backpack> optionalBackpack, Material type) {
        if (!optionalBackpack.isPresent()) return false;
        final Backpack backpack = optionalBackpack.get();
        if (backpack.getStorage().values().stream().mapToInt(m -> m).sum() >= backpack.getMaxContents()) return false;

        backpack.incrementStorage(type);
        return true;
    }

    /**
     * Method to get backpack from slot
     *
     * @param inventory - player inventory
     * @param slot - integer from inventory slot
     * @return backpack {@link  Optional<Backpack>}
     */
    public Optional<Backpack> getBackpackFromSlot(PlayerInventory inventory, int slot) {
        final ItemStack itemStack = inventory.getItem(slot);
        if (itemStack.getType() != Material.ENDER_CHEST) {
            return Optional.empty();
        }

        if (!itemStack.hasItemMeta()) {
            return Optional.empty();
        }

        return Backpacks.from(itemStack);
    }
}
