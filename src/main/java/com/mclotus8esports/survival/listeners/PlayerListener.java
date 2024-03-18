package com.mclotus8esports.survival.listeners;

import com.mclotus8esports.survival.Items;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class PlayerListener implements Listener {
    
    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent e) {
        if (Items.itemIdEquals(e.getItemDrop().getItemStack(), "soul_gem")) {
            e.setCancelled(true);
        }
    }
}
