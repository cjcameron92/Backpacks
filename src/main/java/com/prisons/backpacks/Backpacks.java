package com.prisons.backpacks;

import com.prisons.backpacks.backpack.Backpack;
import com.prisons.backpacks.backpack.BackpackRegistry;
import com.prisons.backpacks.backpack.SimpleBackpackRegistry;
import com.prisons.backpacks.serialization.MaterialSerialization;
import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.EnumMap;
import java.util.Optional;
import java.util.UUID;

/**
 * Backpack utility handler
 */
public class Backpacks {

    private static final String SECRET_KEY = "Backpacks";
    private static final String MATERIAL_KEY = "Materials";
    private static final String CAPACITY_KEY = "Capacity";

    private static final BackpackRegistry registry;

    static {
        registry = new SimpleBackpackRegistry();
    }

    /**
     * Fetch a Backpack from reading the item
     *
     * @param item {@link ItemStack}
     * @return backpack {@link Backpack}
     */
    public static Optional<Backpack> from(@NotNull ItemStack item) {
        final NBTItem nbtItem = new NBTItem(item);

        if (nbtItem.hasKey(SECRET_KEY)) {
            if (registry.getBackpackFromId(nbtItem.getString(SECRET_KEY)).isPresent()) {
                return registry.getBackpackFromId(nbtItem.getString(SECRET_KEY));
            } else {
                final String key = nbtItem.hasKey(SECRET_KEY) ? nbtItem.getString(SECRET_KEY) : UUID.randomUUID().toString();
                final EnumMap<Material, Integer> storage = nbtItem.hasKey(MATERIAL_KEY) ? MaterialSerialization.deserialize(nbtItem.getIntArray(MATERIAL_KEY)) : new EnumMap<Material, Integer>(Material.class);
                final int capacity = nbtItem.hasKey(CAPACITY_KEY) ? nbtItem.getInteger(CAPACITY_KEY) : 0;
                final Backpack backpack = Backpack.create(storage, capacity);
                return registry.register(key, backpack);
            }
        }
        return Optional.empty();
    }

    public static BackpackRegistry getRegistry() {
        return registry;
    }
}
