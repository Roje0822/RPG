package com.github.roje.rpg.command;

import com.github.roje.rpg.data.StatPointData;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class StatPointCmd implements CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String @NotNull [] args) {


        if (sender instanceof Player player) {
            StatPointData data = new StatPointData(player);


            if (args.length == 0) {
                player.sendMessage("" + data.getStatPoint());
                return true;
            }


            switch (args[0]) {

                case "추가", "add" -> {

                    if (args.length == 1) {
                        player.sendMessage("플레이어 닉네임을 입력해주세요!");
                        return true;
                    } else if (args.length == 2) {
                        player.sendMessage("추가할 스탯 포인트를 입력해주세요!");
                        return true;
                    }

                    try {
                        Player target = Bukkit.getPlayer(args[1]);
                        Integer amount = Integer.parseInt(args[2]);
                        data.addStatPoint(amount, target);
                        player.sendMessage("스탯 포인트를 " + amount + " 만큼 추가하였습니다");
                    } catch (NumberFormatException e) {
                        player.sendMessage("잘못된 명령어 형식입니다");
                    }
                    return true;
                }
                case "차감", "minus" -> {

                    if (args.length == 1) {
                        player.sendMessage("플레이어 닉네임을 입력해주세요!");
                        return true;
                    } else if (args.length == 2) {
                        player.sendMessage("차감할 스탯 포인트를 입력해주세요!");
                        return true;
                    }

                    try {
                        Player target = Bukkit.getPlayer(args[1]);
                        Integer amount = Integer.parseInt(args[2]);
                        data.subtractStatPoint(amount, target);
                        player.sendMessage("스탯 포인트를 " + amount + " 만큼 차감하였습니다");
                    } catch (NumberFormatException e) {
                        player.sendMessage("잘못된 명령어 형식입니다");
                    }
                    return true;
                }
                case "설정", "set" -> {

                    if (args.length == 1) {
                        player.sendMessage("플레이어 닉네임을 입력해주세요!");
                        return true;
                    } else if (args.length == 2) {
                        player.sendMessage("설정할 스탯 포인트를 입력해주세요!");
                        return true;
                    }

                    try {
                        Player target = Bukkit.getPlayer(args[1]);
                        Integer amount = Integer.parseInt(args[2]);
                        data.setStatPoint(amount, target);
                        player.sendMessage("스탯 포인트를 " + amount + " 으로 설정하였습니다");
                    } catch (NumberFormatException e) {
                        player.sendMessage("잘못된 명령어 형식입니다");
                    }
                    return true;
                }
                case "확인", "check" -> {
                    Player target = Bukkit.getPlayer(args[1]);
                    player.sendMessage("" + data.checkStatPoint(target));
                }
            }
        }
        return false;
    }
}
