package com.github.roje.rpg;

import com.github.nicklib.data.Config;
import com.github.roje.rpg.command.*;
import com.github.roje.rpg.command.tabcomplete.MoneyTabComplete;
import com.github.roje.rpg.command.tabcomplete.PrefixTabComplete;
import com.github.roje.rpg.command.tabcomplete.StatsTabComplete;
import com.github.roje.rpg.event.InventoryClickListener;
import com.github.roje.rpg.event.PlayerChatListner;
import com.github.roje.rpg.event.PlayerInteractListener;
import com.github.roje.rpg.event.PlayerJoinListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static Main plugin;

    @Override
    public void onEnable() {
        plugin = this;
        init();
        System.out.println(ChatColor.GREEN + "RPG 플러그인이 켜졌습니다.");
    }

    @Override
    public void onDisable() {

        System.out.println(ChatColor.RED + "RPG 플러그인이 꺼졌습니다.");
    }

    public void init() {

        // CONFIG
        Config config = new Config("config", this);
        config.loadDefaultConfig();

        // EVENT
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerChatListner(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerInteractListener(), this);
        Bukkit.getPluginManager().registerEvents(new InventoryClickListener(), this);

        // COMMAND
        Bukkit.getPluginCommand("돈").setExecutor(new MoneyCmd());
        Bukkit.getPluginCommand("칭호").setExecutor(new PrefixCmd());
        Bukkit.getPluginCommand("스탯포인트").setExecutor(new StatPointCmd());
        Bukkit.getPluginCommand("스탯").setExecutor(new StatCmd());

        // TABCOMPLETE
        Bukkit.getPluginCommand("돈").setTabCompleter(new MoneyTabComplete());
        Bukkit.getPluginCommand("칭호").setTabCompleter(new PrefixTabComplete());
        Bukkit.getPluginCommand("스탯포인트").setTabCompleter(new StatsTabComplete());

    }
}
