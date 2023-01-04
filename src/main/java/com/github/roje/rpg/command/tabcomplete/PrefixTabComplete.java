package com.github.roje.rpg.command.tabcomplete;

import com.github.roje.rpg.data.PrefixData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PrefixTabComplete implements TabCompleter {

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        List<String> completions = new ArrayList<>();
        Player player = (Player) sender;
        PrefixData data = new PrefixData(player);

        if (sender instanceof Player) {
            if (player.isOp()) {
                if (args.length == 1) {
                    StringUtil.copyPartialMatches(args[0], Arrays.asList("목록", "장착", "북"), completions);
                } else if (args[0].equals("장착")) {
                    for (List<String> prefix : Arrays.asList(data.getPrefixTabList())) {
                        StringUtil.copyPartialMatches(args[1], data.getPrefixTabList(), completions);
                    }
                } else if (args[0].equals("북")) {
                    StringUtil.copyPartialMatches(args[1], Arrays.asList("<Name>"), completions);
                }
            } else {
                if (args.length == 1) {
                    StringUtil.copyPartialMatches(args[0], Arrays.asList("목록", "장착"), completions);
                } else if (args[0].equals("장착")) {
                    for (List<String> prefix : Arrays.asList(data.getPrefixTabList())) {
                        StringUtil.copyPartialMatches(args[1], data.getPrefixTabList(), completions);
                    }
                }
            }

        }

        return completions;
    }
}
