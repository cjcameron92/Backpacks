package com.prisons.backpacks.backpack;

import me.lucko.helper.Services;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

import java.util.EnumMap;

public interface Backpack extends BaseBackpack {

    static @NotNull Backpack create(EnumMap<Material, Integer> storage, int maxContents) {
        return Services.load(BackpackFactory.class).makeBackpack(storage, maxContents, $ -> {});

    }

    static @NotNull Backpack create(int maxContents) {
        return create(new EnumMap<>(Material.class), maxContents);
    }
}
