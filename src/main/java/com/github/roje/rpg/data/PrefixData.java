package com.github.roje.rpg.data;

import com.github.nicklib.data.Config;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

import static com.github.roje.rpg.Main.plugin;

public class PrefixData {

    private Player player;

    public PrefixData(Player player) {
        this.player = player;
    }


    public void getTest() {
        player.sendMessage(getPrefixUsage());
        Config config = new Config("data/" + player.getUniqueId(), plugin);
        for (String list : config.getConfig().getStringList("prefix.has")) {
            player.sendMessage(list);
        }
    }


    public void selectPrefix(String prefix) {
        Config config = new Config("data/" + player.getUniqueId(), plugin);
        for (String prefixList : config.getConfig().getStringList("prefix.has")) {
            if (prefix.equalsIgnoreCase(prefixList)) {
                config.setString("prefix.usage", prefix);
                config.saveConfig();
                player.sendMessage(prefix + "착용!");
                return;
            }
        }
        player.sendMessage("칭호를 가지고 있지 않습니다");
    }


    public void createPrefixBook(String prefix) {
        ItemStack stack = new ItemStack(Material.ENCHANTED_BOOK);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName("§6[칭호북] §f" + prefix);
        meta.setLore(List.of("§f 우클릭시 칭호를 흭득 합니다"));
        stack.setItemMeta(meta);
        if (player.isOp()) {
            player.getInventory().addItem(stack);
            player.sendMessage(prefix + " 라는 이름의 칭호북을 지급하였습니다.");
        } else {
            player.sendMessage("권한이 없습니다");
        }
    }


    public List<String> getPrefixTabList() {
        Config config = new Config("data/" + player.getUniqueId(), plugin);
        return config.getConfig().getStringList("prefix.has");
    }


    public void getPrefixList() {
        Config config = new Config("data/" + player.getUniqueId(), plugin);
        for (String list : config.getStringList("prefix.has")) {
            player.sendMessage(list);
        }
    }

    public String getPrefixUsage() {
        Config config = new Config("data/" + player.getUniqueId(), plugin);
        return config.getString("prefix.usage");
    }
}
