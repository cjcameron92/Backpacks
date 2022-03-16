package com.prisons.backpacks.backpack;

import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

import java.util.EnumMap;
import java.util.function.Consumer;

public class SimpleBackpackFactory implements BackpackFactory {

    @Override public @NotNull Backpack makeBackpack(EnumMap<Material, Integer> storage, long maxContents, @NotNull Consumer<Backpack> consumer) {
        return new SimpleBackpack(storage, maxContents);
    }

    private static final class SimpleBackpack implements Backpack {

        private final EnumMap<Material, Integer> storage;
        private final long maxContents;

        public SimpleBackpack(EnumMap<Material, Integer> storage, long maxContents) {
            this.storage = storage;
            this.maxContents = maxContents;
        }

        @Override public @NotNull EnumMap<Material, Integer> getStorage() {
            return this.storage;
        }

        @Override public long getMaxContents() {
            return this.maxContents;
        }
    }
}
