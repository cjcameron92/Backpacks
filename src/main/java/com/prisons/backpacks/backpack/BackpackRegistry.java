package com.prisons.backpacks.backpack;

import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public interface BackpackRegistry {

    @NotNull Optional<Backpack> getBackpackFromId(@NotNull String key);

    @NotNull Optional<Backpack> register(@NotNull String key, @NotNull Backpack backpack);

}
