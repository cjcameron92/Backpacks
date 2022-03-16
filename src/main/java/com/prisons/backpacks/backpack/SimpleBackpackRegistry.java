package com.prisons.backpacks.backpack;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class SimpleBackpackRegistry implements BackpackRegistry {

    private final Map<String, Backpack> backpacks = new HashMap<>();

    @Override public @NotNull Optional<Backpack> getBackpackFromId(@NotNull String key) {
        return Optional.of(this.backpacks.get(key));
    }

    @Override public @NotNull Optional<Backpack> register(@NotNull String key, @NotNull Backpack backpack) {
        return Optional.ofNullable(this.backpacks.put(key, backpack));
    }
}
