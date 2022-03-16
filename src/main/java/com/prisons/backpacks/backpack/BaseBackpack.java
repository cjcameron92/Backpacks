package com.prisons.backpacks.backpack;


import com.prisons.backpacks.event.BackpackAddItemEvent;
import com.prisons.backpacks.event.BackpackClearEvent;
import me.lucko.helper.Helper;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

import java.util.EnumMap;

public interface BaseBackpack {

    @NotNull EnumMap<Material, Integer> getStorage();

    long getMaxContents();

    default void incrementStorage(@NotNull Material type) {
        getStorage().put(type, getStorage().getOrDefault(type, 0) + 1);
        Helper.server().getPluginManager().callEvent(new BackpackAddItemEvent(this, type, 1));
    }

    default void setStorage(@NotNull Material type, int amount) {
        getStorage().put(type, getStorage().getOrDefault(type, 0) + amount);
        Helper.server().getPluginManager().callEvent(new BackpackAddItemEvent(this, type, amount));
    }

    default void clear() {
        getStorage().clear();
        Helper.server().getPluginManager().callEvent(new BackpackClearEvent(this));
    }
}
