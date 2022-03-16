package com.prisons.backpacks.backpack;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public final class BackpackProps {

    private final FileConfiguration configuration;

    private Set<Material> blacklistedTypes;
    private Set<String> blacklistedWorlds;

    public BackpackProps(FileConfiguration configuration) {
        this.configuration = configuration;

        cache();
    }

    public void cache() {
        blacklistedTypes = configuration.getStringList("blacklisted-types").stream().map(type -> Material.valueOf(type)).collect(Collectors.toSet());
        blacklistedWorlds = new HashSet<>(configuration.getStringList("blacklisted-worlds"));
    }

    public Set<Material> getBlacklistedTypes() {
        return blacklistedTypes;
    }

    public Set<String> getBlacklistedWorlds() {
        return blacklistedWorlds;
    }

    public FileConfiguration getConfiguration() {
        return configuration;
    }
}
