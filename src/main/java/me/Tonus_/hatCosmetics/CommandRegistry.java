package me.Tonus_.hatCosmetics;

import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import me.Tonus_.hatCosmetics.manager.cosmetic.CosmeticsManager;
import me.Tonus_.hatCosmetics.manager.inventory.InventoryManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

// TODO: Custom command completions
// TODO: All text based on locale

@CommandAlias("hatcosmetics|hats")
public class CommandRegistry extends co.aikar.commands.BaseCommand  {
    private final InventoryManager inventoryHandler;
    private final CosmeticsManager cosmeticsManager;

    public CommandRegistry(InventoryManager inventoryManager, CosmeticsManager cosmeticsManager) {
        this.inventoryHandler = inventoryManager;
        this.cosmeticsManager = cosmeticsManager;
    }

    @Default
    @Description("Open HatCosmetics GUI")
    private void onDefault(@NotNull Player player) {
        this.inventoryHandler.openInventory(player);
    }

    @Subcommand("equip|eq|e")
    @Description("Equip specified cosmetic")
    @Syntax("<COSMETIC_NAME>")
    private void onEquip(@NotNull Player player, @Single String cosmeticName) {
        this.cosmeticsManager.equip(player, cosmeticName);
    }

    @Subcommand("unequip|ueq|ue")
    @Description("Unequip current hat")
    private void onUnequip(@NotNull Player player) {
        this.cosmeticsManager.unequip(player);
    }

    @Subcommand("reload")
    @CommandPermission("hatcosmetics.reload")
    @Description("Reload HatCosmetics Plugin")
    private void onReload(@NotNull CommandSender sender) {
        sender.sendMessage("TBD: Reload");
        // TODO: Implement
    }

    @HelpCommand
    @Description("View help")
    @Syntax("[command]")
    private void onHelp(@NotNull CommandHelp help) {
        help.showHelp();
    }

    @CatchUnknown
    private void onUnknown(@NotNull CommandSender sender) {
        sender.sendMessage("Command not found!");
    }
}
