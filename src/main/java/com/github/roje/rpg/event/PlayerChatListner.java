package com.github.roje.rpg.event;

import com.github.roje.rpg.data.PrefixData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("all")
public class PlayerChatListner implements Listener {


    @EventHandler
    public void onChat(@NotNull PlayerChatEvent event) {
        Player player = event.getPlayer();
        PrefixData data = new PrefixData(player);
        String chat = event.getMessage();
        if (data.getPrefixUsage() != null) {
            event.setCancelled(true);
            Bukkit.broadcastMessage(" " + data.getPrefixUsage() + " " + player.getName() + "  " + chat);
        } else {
            event.setCancelled(true);
            Bukkit.broadcastMessage(" " + player.getName() + "  " + chat);
        }
    }
}
