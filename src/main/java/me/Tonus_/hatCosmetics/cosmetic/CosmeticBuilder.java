package me.Tonus_.hatCosmetics.cosmetic;

import lombok.AllArgsConstructor;
import me.Tonus_.hatCosmetics.manager.cosmetic.CosmeticTagManager;
import org.bukkit.inventory.ItemStack;


@AllArgsConstructor
public class CosmeticBuilder {
    private static final String HC_PREFIX = "hatcosmetics.hat.";
    private CosmeticTagManager tagManager;

    public Cosmetic create(
        String name,
        ItemStack itemStack,
        CosmeticType cosmeticType
    ) {
        ItemStack itemStackWithTags = tagManager.addCosmeticTag(itemStack, name);

        return new Cosmetic(
            name,
            HC_PREFIX + name,
            itemStackWithTags,
            cosmeticType
        );
    }
}
