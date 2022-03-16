package com.prisons.backpacks.event;

import com.prisons.backpacks.backpack.BaseBackpack;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class BaseBackpackEvent extends Event {

    private final BaseBackpack backpack;

    public BaseBackpackEvent(BaseBackpack backpack) {
        this.backpack = backpack;
    }

    public BaseBackpack getBackpack() {
        return backpack;
    }

    @Override public HandlerList getHandlers() {
        return null;
    }
}
