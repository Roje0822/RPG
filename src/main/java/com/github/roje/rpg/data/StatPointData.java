package com.github.roje.rpg.data;

import com.github.nicklib.data.Config;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static com.github.roje.rpg.Main.plugin;

public class StatPointData {

    private Player player;

    public StatPointData(Player player) {
        this.player = player;
    }


    public void addStatPoint(int amount, @NotNull Player target) {
        Config config = new Config("data/" + target.getUniqueId(), plugin);
        config.setLong("stat.point", getStatPoint() + amount);
    }


    public void setStatPoint(int amount, @NotNull Player target) {
        Config config = new Config("data/" + target.getUniqueId(), plugin);
        config.setLong("stat.point", amount);
    }


    public void subtractStatPoint(int amount, @NotNull Player target) {
        Config config = new Config("data/" + target.getUniqueId(), plugin);
        config.setLong("stat.point", getStatPoint() - amount);
    }


    public Integer checkStatPoint(@NotNull Player target) {
        Config config = new Config("data/" + target.getUniqueId(), plugin);
        return config.getInt("stat.point");
    }

    public Integer getStatPoint() {
        Config config = new Config("data/" + player.getUniqueId(), plugin);
        return config.getInt("stat.point");
    }
}
