package me.Tonus_.hatCosmetics.structs.cosmetic;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import javax.annotation.Nullable;
import java.util.Optional;


public class CosmeticItem extends ItemStack {
    private Optional<Cosmetic> cosmetic;

    boolean isOverlay() {
        return this.getType() != Material.AIR;
    }

    void applyCosmetic(@Nullable Cosmetic cosmetic) {
        this.cosmetic = Optional.ofNullable(cosmetic);
    }

    void removeCosmetic() {
        this.cosmetic = Optional.empty();
    }
}
