package net.tokyosu.raritymod.utils;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.ForgeRegistries;
import net.tokyosu.raritymod.Config;
import net.tokyosu.raritymod.Constants;

public class RarityUtils {

    public static Rarity GetRarityFromNBT(CompoundTag nbt, Rarity oldRarity) {
        if (nbt.contains("Lore")) {
            CompoundTag displayTag = nbt.getCompound("text");
            if (displayTag.contains("Lore")) {
                String name = displayTag.getString("Lore");
                if (name.contains("逢")) {
                    return Constants.RARITY_COMMON;
                } else if (name.contains("恱")) {
                    return Constants.RARITY_UNCOMMON;
                } else if (name.contains("恲")) {
                    return Constants.RARITY_RARE;
                } else if (name.contains("恳")) {
                    return Constants.RARITY_EPIC;
                } else if (name.contains("恴")) {
                    return Constants.RARITY_LEGENDARY;
                } else if (name.contains("恵")) {
                    return Constants.RARITY_MYTHIC;
                } else if (name.contains("戥")) {
                    return Constants.RARITY_GOD;
                }
            }
        }

        if (nbt.contains("MMOITEMS_TIER")) {
            String tier = nbt.getString("MMOITEMS_TIER");
            switch (tier) {
                case "COMMON":
                    return Constants.RARITY_UNCOMMON;
                case "UNCOMMON":
                    return Constants.RARITY_COMMON;
                case "RARE":
                    return Constants.RARITY_RARE;
                case "ELITE":
                    return Constants.RARITY_EPIC;
                case "LEGENDARY":
                    return Constants.RARITY_LEGENDARY;
                case "LIMITED":
                    return Constants.RARITY_MYTHIC;
                case "MYTHICAL":
                    return Constants.RARITY_GOD;
            }
        }
        return oldRarity;
    }

    public static Rarity GetRarityFromResourceLocation(ResourceLocation location, Rarity oldRarity) {
        if (Config.RARITY_GOD_LIST.contains(location))
            return Constants.RARITY_GOD;
        else if (Config.RARITY_MYTHIC_LIST.contains(location))
            return Constants.RARITY_MYTHIC;
        else if (Config.RARITY_UNIQUE_LIST.contains(location))
            return Constants.RARITY_UNIQUE;
        else if (Config.RARITY_LEGENDARY_LIST.contains(location))
            return Constants.RARITY_LEGENDARY;
        else if (Config.RARITY_EPIC_LIST.contains(location))
            return Constants.RARITY_EPIC;
        else if (Config.RARITY_RARE_LIST.contains(location))
            return Constants.RARITY_RARE;
        else if (Config.RARITY_UNCOMMON_LIST.contains(location))
            return Constants.RARITY_UNCOMMON;
        else if (Config.RARITY_COMMON_LIST.contains(location))
            return Constants.RARITY_COMMON;
        else return oldRarity;
    }
}