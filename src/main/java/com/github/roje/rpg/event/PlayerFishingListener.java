package com.github.roje.rpg.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;

public class PlayerFishingListener implements Listener {

    public void onFishing(PlayerFishEvent event) {

        Player player = event.getPlayer();

        if (event.getState() == PlayerFishEvent.State.CAUGHT_FISH) {
            player.sendMessage("낚시 성공");
        }

    }
}
