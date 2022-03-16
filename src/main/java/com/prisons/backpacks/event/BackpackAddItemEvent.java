package com.prisons.backpacks.event;

import com.prisons.backpacks.backpack.BaseBackpack;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

public class BackpackAddItemEvent extends BaseBackpackEvent {

    private final Material type;
    private final long amount;

    public BackpackAddItemEvent(@NotNull BaseBackpack backpack, @NotNull Material type, long amount) {
        super(backpack);
        this.type = type;
        this.amount = amount;
    }

    public Material getType() {
        return type;
    }

    public long getAmount() {
        return amount;
    }
}
