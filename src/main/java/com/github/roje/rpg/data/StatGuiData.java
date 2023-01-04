package com.github.roje.rpg.data;

import com.github.nicklib.data.Config;
import com.github.nicklib.utils.ItemUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static com.github.roje.rpg.Main.plugin;

public class StatGuiData {

    private Player player;



    public StatGuiData(Player player) {
        this.player = player;
    }


    public void openInventory() {
        Inventory inv = Bukkit.createInventory(null, 27, "스탯창");
        init(inv);
        player.openInventory(inv);
    }


    private void init(@NotNull Inventory inv) {

        Config config = new Config("data/" + player.getUniqueId(), plugin);

        inv.setItem(10, createItem(Material.ENCHANTED_BOOK, "공격력", "공격력 포인트 : " + config.getInt("stat.attack")));
        inv.setItem(12, createItem(Material.ENCHANTED_BOOK, "방어력", "방어력 포인트 :" + config.getInt("stat.defense")));
        inv.setItem(14, createItem(Material.ENCHANTED_BOOK, "체력", "체력 포인트 : " + config.getInt("stat.health")));
        inv.setItem(16, createItem(Material.ENCHANTED_BOOK, "민첩", "민첩 포인트 : " + config.getInt("stat.agility")));


    }

    public void statPointUp(String stat, int remainPoint) {

        if (remainPoint == 0) {
            player.sendMessage("남은 스탯 포인트가 없습니다");
            return;
        } else {
            Config config = new Config("data/" + player.getUniqueId(), plugin);
            switch (stat) {
                case "공격력" -> {
                    config.setInt("stat.attack", config.getInt("stat.attack") + 1);
                    config.setInt("stat.point", config.getInt("stat.point") - 1);
                    player.sendMessage("공격력 +1");

                    break;
                }
                case "방어력" -> {
                    config.setInt("stat.defense", config.getInt("stat.defense") + 1);
                    config.setInt("stat.point", config.getInt("stat.point") - 1);
                    player.sendMessage("방어력 +1");

                    break;
                }
                case "체력" -> {
                    config.setInt("stat.health", config.getInt("stat.health") + 1);
                    config.setInt("stat.point", config.getInt("stat.point") - 1);
                    player.sendMessage("체력 +1");

                    break;
                }
                case "민첩" -> {
                    config.setInt("stat.agility", config.getInt("stat.agility") + 1);
                    config.setInt("stat.point", config.getInt("stat.point") - 1);
                    player.sendMessage("민첩 +1");

                    break;
                }

            }
        }

    }


    private @NotNull ItemStack createItem(Material material, String name, String... lore) {
        ItemStack itemStack = new ItemStack(material, 1);
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.setDisplayName(name);
        itemMeta.setLore(List.of(lore));

        itemStack.setItemMeta(itemMeta);


        return itemStack;
    }



    public void updateInventory(@NotNull Inventory inv) {

        Config config = new Config("data/" + player.getUniqueId(), plugin);

        inv.setItem(10, createItem(Material.ENCHANTED_BOOK, "공격력", "공격력 포인트 : " + config.getInt("stat.attack")));
        inv.setItem(12, createItem(Material.ENCHANTED_BOOK, "방어력", "방어력 포인트 :" + config.getInt("stat.defense")));
        inv.setItem(14, createItem(Material.ENCHANTED_BOOK, "체력", "체력 포인트 : " + config.getInt("stat.health")));
        inv.setItem(16, createItem(Material.ENCHANTED_BOOK, "민첩", "민첩 포인트 : " + config.getInt("stat.agility")));
    }
}
