package com.github.roje.rpg.event;

import com.github.nicklib.data.Config;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static com.github.roje.rpg.Main.plugin;

public class PlayerJoinListener implements Listener {


    @EventHandler
    public void onJoin(@NotNull PlayerJoinEvent event) {

        Player player = event.getPlayer();
        Config config = new Config("data/" + player.getUniqueId(), plugin);
        if (!config.isFileExist()) {
            config.setInt("stat.attack", 0);
            config.setInt("stat.defense", 0);
            config.setInt("stat.health", 0);
            config.setInt("stat.agility", 0);
            config.setLong("money", 0l);
            config.setString("prefix.usage", "test");
            config.getConfig().set("prefix.has", new ArrayList<>());
            config.setInt("stat.point", 0);
            config.saveConfig();
        }
    }
}
