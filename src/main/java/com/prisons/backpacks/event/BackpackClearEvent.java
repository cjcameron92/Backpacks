package com.prisons.backpacks.event;

import com.prisons.backpacks.backpack.BaseBackpack;
import org.jetbrains.annotations.NotNull;

public class BackpackClearEvent extends BaseBackpackEvent {

    public BackpackClearEvent(@NotNull BaseBackpack backpack) {
        super(backpack);
    }
}
