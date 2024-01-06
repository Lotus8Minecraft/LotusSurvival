package com.mclotus8esports.survival.listeners;

import com.mclotus8esports.survival.Items;
import com.mclotus8esports.survival.LotusSurvival;
import com.stardevllc.staritems.CustomItem;
import com.stardevllc.staritems.ItemKey;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockReceiveGameEvent;
import org.bukkit.inventory.ItemStack;

public class BlockListener implements Listener {
    
    private LotusSurvival plugin;

    public BlockListener(LotusSurvival plugin) {
        this.plugin = plugin;
    }
    
    @EventHandler
    public void blockReceiveGameEvent(BlockReceiveGameEvent e) {
        if (e.getBlock().getType() == Material.SCULK_SENSOR || e.getBlock().getType() == Material.CALIBRATED_SCULK_SENSOR) {
            if (e.getEntity() instanceof Player player) {
                ItemStack helmet = player.getInventory().getHelmet();
                ItemStack chestplate = player.getInventory().getChestplate();
                ItemStack leggings = player.getInventory().getLeggings();
                ItemStack boots = player.getInventory().getBoots();
                if (helmet != null && chestplate != null && leggings != null && boots != null) {
                    ItemKey helmetKey = CustomItem.getKeyFromItem(helmet);
                    if (!helmetKey.getNamespace().equals("lotussurvival") && !helmetKey.getKey().equals(Items.wardenHelmet.getKey().getKey())) {
                        return;
                    }

                    ItemKey chestplateKey = CustomItem.getKeyFromItem(chestplate);
                    if (!chestplateKey.getNamespace().equals("lotussurvival") && !chestplateKey.getKey().equals(Items.wardenChestplate.getKey().getKey())) {
                        return;
                    }

                    ItemKey leggingsKey = CustomItem.getKeyFromItem(leggings);
                    if (!leggingsKey.getNamespace().equals("lotussurvival") && !leggingsKey.getKey().equals(Items.wardenLeggings.getKey().getKey())) {
                        return;
                    }

                    ItemKey bootsKey = CustomItem.getKeyFromItem(boots);
                    if (!bootsKey.getNamespace().equals("lotussurvival") && !bootsKey.getKey().equals(Items.wardenBoots.getKey().getKey())) {
                        return;
                    }
                    
                    e.setCancelled(true);
                }
            }
        }
    }
}
