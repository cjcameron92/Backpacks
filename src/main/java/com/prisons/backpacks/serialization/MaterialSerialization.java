package com.prisons.backpacks.serialization;

import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

import java.util.EnumMap;
import java.util.Map;

public class MaterialSerialization {


    /**
     * Store enchants as an array of integers
     * <p>
     * Each integer in the array is in the following format:
     * * first 8 bits: id (unsigned byte)
     * * last 24 bits: level (unsigned int)
     *
     * @param enchants Enchants mapped to their level
     * @return serialized int array as described above
     */
    public static int[] serialize(@NotNull Map<Material, Integer> enchants) {
        int[] arr = new int[enchants.size()];
        int index = 0;

        for (Map.Entry<Material, Integer> entry : enchants.entrySet()) {
            arr[index++] = (entry.getKey().ordinal() << 24) | (entry.getValue() & 0xFFFFFF);
        }

        return arr;
    }

    /**
     * Deserialize enchants stored as an integer array.
     * See method above for formatting information
     *
     * @param enchantArr Array of serialized ints
     * @return Map of the enchant types to their respective levels
     */
    public static EnumMap<Material, Integer> deserialize(int[] enchantArr) {
        EnumMap<Material, Integer> map = new EnumMap<>(Material.class);

        for (int packed : enchantArr) {
            map.put(Material.values()[packed >> 24], packed & 0xFFFFFF);
        }

        return map;
    }
}
