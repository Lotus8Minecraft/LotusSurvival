package com.mclotus8esports.survival;

import com.stardevllc.staritems.CustomItem;
import com.stardevllc.starmclib.item.ItemBuilder;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.function.Consumer;

public class SurvivalItem extends CustomItem {
    public SurvivalItem(String id, ItemBuilder itemBuilder) {
        super("lotussurvival", id, itemBuilder);
    }

    public SurvivalItem(String id, ItemBuilder itemBuilder, Consumer<PlayerInteractEvent> interactConsumer) {
        super("lotussurvival", id, itemBuilder, interactConsumer);
    }
}