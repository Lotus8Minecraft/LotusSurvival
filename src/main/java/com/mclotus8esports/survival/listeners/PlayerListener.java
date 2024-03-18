package com.mclotus8esports.survival.listeners;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import com.mclotus8esports.survival.Items;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerListener implements Listener {

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent e) {
        if (Items.itemIdEquals(e.getItemDrop().getItemStack(), "soul_gem")) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onArmorChange(PlayerArmorChangeEvent e) {
        Player player = e.getPlayer();
        if (e.getSlotType() == PlayerArmorChangeEvent.SlotType.CHEST) {
            if (Items.itemIdEquals(e.getOldItem(), Items.wardenChestplate.getKey().getKey())) {
                player.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
            }
            
            if (Items.itemIdEquals(e.getNewItem(), Items.wardenChestplate.getKey().getKey())) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 2, false, false));
            }
        }
    }
}
