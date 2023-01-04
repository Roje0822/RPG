package com.github.roje.rpg.command;

import com.github.roje.rpg.data.PrefixData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PrefixCmd implements CommandExecutor {


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player player) {
            PrefixData data = new PrefixData(player);
            if (args.length == 0) {
                player.sendMessage("현재 사용 중인 칭호는" + data.getPrefixUsage() + "입니다");
                return true;
            }
            switch (args[0]) {
                case "목록", "list" -> {
                    player.sendMessage("보유 칭호 목록");
                    data.getPrefixList();
                    return true;
                }
                case "장착" -> {
                    if (args.length == 1) {
                        player.sendMessage("칭호를 입력해주세요");
                        return true;
                    }
                    String prefix = args[1];
                    data.selectPrefix(prefix);
                    return true;
                }
            }
            if (player.isOp()) {
                switch (args[0]) {
                    case "북" -> {
                        if (args.length == 1) {
                            player.sendMessage("칭호 이름을 입력해주세요!");
                            return true;
                        }
                        String prefix = args[1];
                        data.createPrefixBook(prefix);

                        return true;
                    }
                }
            }
        }

        return false;
    }
}
