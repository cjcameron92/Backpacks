package com.prisons.backpacks.backpack;

import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

import java.util.EnumMap;
import java.util.function.Consumer;

public interface BackpackFactory {

    @NotNull Backpack makeBackpack(EnumMap<Material, Integer> storage, long maxContents, @NotNull Consumer<Backpack> consumer);

    default Backpack makeBackpack() {
        return makeBackpack(new EnumMap<Material, Integer>(Material.class), 100, $ -> {});
    }
}
