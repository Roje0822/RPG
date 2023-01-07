package com.github.roje.rpg.event;

import com.github.roje.rpg.data.StatGuiData;
import com.github.roje.rpg.data.StatPointData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickListener implements Listener {


    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        StatGuiData statGuiData = new StatGuiData(player);
        StatPointData statPointData = new StatPointData(player);

        if(event.getView().getTitle().equalsIgnoreCase("스탯창")) {

            int remainPoint = statPointData.checkStatPoint(player);

            if(event.getSlot() == 10) {
                statGuiData.statPointUp("공격력", remainPoint);
                statGuiData.updateInventory(event.getInventory());
            }

            if (event.getSlot() == 12) {
                statGuiData.statPointUp("방어력", remainPoint);
                statGuiData.updateInventory(event.getInventory());
            }

            if (event.getSlot() == 14) {
                statGuiData.statPointUp("체력", remainPoint);
                statGuiData.updateInventory(event.getInventory());
            }

            if (event.getSlot() == 16) {
                statGuiData.statPointUp("민첩", remainPoint);
                statGuiData.updateInventory(event.getInventory());
            }
            event.setCancelled(true);

        }
    }
}
