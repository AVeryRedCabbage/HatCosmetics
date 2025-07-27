package me.Tonus_.hatCosmetics.cosmetic;

import org.bukkit.inventory.ItemStack;


public record Cosmetic(
    String name,
    String permission,
    ItemStack itemStack,
    CosmeticType cosmeticType
) {}
