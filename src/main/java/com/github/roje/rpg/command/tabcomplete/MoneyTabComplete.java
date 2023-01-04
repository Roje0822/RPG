package com.github.roje.rpg.command.tabcomplete;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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

@SuppressWarnings("all")
public class MoneyTabComplete implements TabCompleter {

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        List<String> completions = new ArrayList<>();
        Player player = (Player) sender;

        if (sender instanceof Player) {
            if (player.isOp()) {
                if (args.length == 1) {
                    StringUtil.copyPartialMatches(args[0], Arrays.asList("지급", "차감", "설정", "초기화", "확인", "보내기"), completions);
                }
                else if (args[0].equals("지급") || args[0].equals("차감") || args[0].equals("설정") || args[0].equals("보내기")) {
                    if (args.length == 2) {
                        for (Player target : Bukkit.getOnlinePlayers()) {
                            completions.add(target.getName());
                        }
                    }
                    else if (args.length == 3) {
                        StringUtil.copyPartialMatches(args[2], Arrays.asList("<Amount>"), completions);
                    }
                }
                else if (args[0].equals("확인") || args[0].equals("초기화")) {
                    if (args.length == 2) {
                        for (Player target : Bukkit.getOnlinePlayers()) {
                            completions.add(target.getName());
                        }
                    }
                }
            }
            else {
                if (args.length == 1) {
                    StringUtil.copyPartialMatches(args[0], Arrays.asList("확인", "보내기"), completions);
                }
                if (args[0].equals("보내기")) {
                    if (args.length == 2) {
                        for (Player target : Bukkit.getOnlinePlayers()) {
                            completions.add(target.getName());
                        }
                    }
                    else if (args.length == 3) {
                        StringUtil.copyPartialMatches(args[2], Arrays.asList("<Amount>"), completions);
                    }
                }
                else if (args[0].equals("확인")) {
                    if (args.length == 2) {
                        for (Player target : Bukkit.getOnlinePlayers()) {
                            completions.add(target.getName());
                        }
                    }
                }
            }
        } else {
            System.out.println(ChatColor.RED + "버킷에서는 명령어를 사용 할 수 없습니다.");
        }
        return completions;
        }
    }
