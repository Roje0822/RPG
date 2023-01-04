package com.github.roje.rpg.event;

import com.github.nicklib.data.Config;
import com.github.roje.rpg.data.PrefixData;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static com.github.roje.rpg.Main.plugin;

public class PlayerInteractListener implements Listener {


    @EventHandler
    public void onRightClick(@NotNull PlayerInteractEvent event) {

        Player player = event.getPlayer();
        Material material = event.getMaterial();
        PrefixData data = new PrefixData(player);
        Config config = new Config("data/" + player.getUniqueId(), plugin);
        ItemStack item = event.getItem();

        if (item == null) return;
        String itemName = item.getItemMeta().getDisplayName().replace("§6[칭호북] §f", "");

        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {

            if (material == Material.ENCHANTED_BOOK) {


                if (event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6[칭호북] §f" + itemName)) {


                    for (String prefixList : config.getConfig().getStringList("prefix.has")) {
                        if (itemName.equalsIgnoreCase(prefixList)) {
                            player.sendMessage("이미 있는 칭호입니다!");
                            return;
                        }
                    }
                    List<String> prefix = config.getConfig().getStringList("prefix.has");
                    prefix.add(itemName);

                    config.setStringList("prefix.has", prefix);
                    config.saveConfig();
                    player.getInventory().removeItem(item);
                    player.sendMessage(" " + itemName + " 칭호를 흭득하였습니다.");
                }
            }
        }
    }
}
