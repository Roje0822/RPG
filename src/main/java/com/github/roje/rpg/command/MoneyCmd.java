package com.github.roje.rpg.command;

import com.github.roje.rpg.data.MoneyData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@SuppressWarnings("all")
public class MoneyCmd implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player player) {

            MoneyData data = new MoneyData(player);
            Player target;

            if (args.length == 0) {
                player.sendMessage("" + data.getMoney());
                return true;
            } else {
                if (player.isOp()) {
                    switch (args[0]) {
                        case "지급", "더하기", "plus" -> {
                            if (args.length == 1) {
                                player.sendMessage("플레이어 이름을 입력해주세요");
                                return true;
                            } else if (args.length == 2) {
                                player.sendMessage("수치를 입력해주세요");
                                return true;
                            }
                            try {
                                target = Bukkit.getPlayer(args[1]);
                                Long amount = Long.parseLong(args[2]);
                                data.addMoney(target, amount);
                                player.sendMessage(target.getName() + "에게 " + amount + " 만큼 보냈습니다");
                                target.sendMessage("관리자에게 " + amount + " 만큼 받았습니다");
                            } catch (NumberFormatException e) {
                                player.sendMessage("잘못된 명령어 형식입니다");
                            }
                            return true;
                        }
                        case "차감", "빼기", "minus" -> {
                            try {
                                if (args.length == 1) {
                                    player.sendMessage("플레이어 이름을 입력해주세요");
                                    return true;
                                } else if (args.length == 2) {
                                    player.sendMessage("수치를 입력해주세요");
                                    return true;
                                }
                                target = Bukkit.getPlayer(args[1]);
                                Long amount = Long.parseLong(args[2]);
                                data.subtractMoney(target, amount);
                                player.sendMessage(target.getName() + "의 돈 " + amount + " 만큼 뺐었습니다");
                                target.sendMessage("관리자에게 " + amount + " 만큼 뺐겼습니다");
                            } catch (NumberFormatException e) {
                                player.sendMessage("잘못된 명령어 형식입니다");
                            }

                            return true;
                        }
                        case "설정", "set" -> {
                            try {
                                if (args.length == 1) {
                                    player.sendMessage("플레이어 이름을 입력해주세요");
                                    return true;
                                } else if (args.length == 2) {
                                    player.sendMessage("수치를 입력해주세요");
                                    return true;
                                }
                                target = Bukkit.getPlayer(args[1]);
                                Long amount = Long.parseLong(args[2]);
                                data.setTargetMoney(target, amount);
                                player.sendMessage(target.getName() + "의 돈을 " + amount + " 으로 설정하였습니다");
                                target.sendMessage("관리자가 당신의 돈을 " + amount + " 으로 설정하였습니다");
                            } catch (NumberFormatException e) {
                                player.sendMessage("잘못된 명령어 형식입니다");
                            }

                            return true;
                        }
                        case "초기화" -> {
                            try {
                                if (args.length == 1) {
                                    player.sendMessage("플레이어 이름을 입력해주세요");
                                    return true;
                                }
                                target = Bukkit.getPlayer(args[1]);
                                data.setTargetMoney(target, 0l);
                                player.sendMessage(target.getName() + "의 돈을 초기화 하였습니다");
                                target.sendMessage("관리자가 당신의 돈을 초기화 하였습니다");

                            } catch (Exception e) {
                                player.sendMessage("잘못된 명령어 형식입니다");
                            }

                            return true;
                        }
                        case "모두지급", "allplus" -> {
                            try {
                                if (args.length == 1) {
                                    player.sendMessage("수치를 입력해주세요");
                                    return true;
                                }
                                Long amount = Long.parseLong(args[1]);
                                for (Player allplayer : Bukkit.getOnlinePlayers()) {
                                    data.addMoney(allplayer, amount);
                                    allplayer.sendMessage("관리자로 부터" + amount + "만큼 받았습니다");
                                }
                            } catch (Exception e) {
                                player.sendMessage("잘못된 명령어 형식입니다");
                            }
                        }
                    }
                }
                switch (args[0]) {
                    case "확인", "check" -> {
                        try {
                            target = Bukkit.getPlayer(args[1]);
                            data.getTargetMoney(target);
                            player.sendMessage(target.getName() + "님의 돈은 " + data.getTargetMoney(target) + "입니다.");
                        } catch (Exception e) {
                            player.sendMessage("잘못된 명령어 형식입니다");
                        }

                        return true;
                    }
                    case "보내기", "give" -> {
                        try {
                            target = Bukkit.getPlayer(args[1]);
                            Long amount = Long.parseLong(args[2]);
                            if (data.getMoney() > amount) {
                                if (player == target) {
                                    player.sendMessage("자신에게는 돈을 보낼 수 없습니다.");
                                } else {
                                    data.subtractMoney(player, amount);
                                    data.addMoney(target, amount);
                                    player.sendMessage(target.getName() + "님께" + amount + "만큼 보냈습니다.");
                                    target.sendMessage(player.getName() + "님으로 부터 " + amount + "만큼 받았습니다");
                                }
                            } else {
                                player.sendMessage("소지금이" + amount + " 보다 작습니다.");
                            }
                        } catch (Exception e) {
                            player.sendMessage("잘못된 명령어 형식입니다");
                        }

                    }
                }
            }


        } else {
            System.out.println(ChatColor.RED + "버킷에서는 명령어를 사용 할 수 없습니다.");
        }
        return false;
    }
}
