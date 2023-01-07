package com.github.roje.rpg.command;

import com.github.roje.rpg.data.StatGuiData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class StatCmd implements CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String @NotNull [] args) {

        if (sender instanceof Player player) {

            StatGuiData statGuiData = new StatGuiData(player);

            if(args.length == 0) {
                statGuiData.openInventory();
                return true;
            }
        }
        return false;
    }
}
