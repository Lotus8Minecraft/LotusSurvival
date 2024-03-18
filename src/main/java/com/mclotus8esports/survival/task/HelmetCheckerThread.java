package com.mclotus8esports.survival.task;

import com.mclotus8esports.survival.Items;
import com.mclotus8esports.survival.LotusSurvival;
import com.stardevllc.starmclib.StarThread;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

public class HelmetCheckerThread extends StarThread<LotusSurvival> {
    public HelmetCheckerThread(LotusSurvival plugin) {
        super(plugin, 1L, 1L, false);
    }

    @Override
    public void onRun() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            ItemStack helmet = player.getInventory().getHelmet();
            if (helmet == null) {
                continue;
            }
            
            if (!player.hasPotionEffect(PotionEffectType.DARKNESS)) {
                continue;
            }
            
            if (Items.itemIdEquals(helmet, Items.wardenHelmet.getKey().getKey())) {
                player.removePotionEffect(PotionEffectType.DARKNESS);
            }
        }
    }
}
