package com.github.roje.rpg.command.tabcomplete;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class StatsTabComplete implements TabCompleter {


    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {


        if(sender instanceof Player) {

            if(args.length == 1) {
                return List.of("추가", "차감", "설정");
            }

            if(args.length == 2) {
                for(Player player : Bukkit.getOnlinePlayers()) {
                    return List.of(player.getName());
                }
            }

            if(args.length == 3) {
                return List.of("<수치>");
            }
        }
        return null;
    }
}
