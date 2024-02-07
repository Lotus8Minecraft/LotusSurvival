package com.mclotus8esports.survival.listeners;

import com.mclotus8esports.survival.Items;
import com.mclotus8esports.survival.LotusSurvival;
import com.mclotus8esports.survival.items.SoulGem;
import com.stardevllc.starmclib.color.ColorUtils;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.List;
import java.util.Random;
import java.util.Set;

import static org.bukkit.event.entity.EntityDamageEvent.DamageCause.*;

@SuppressWarnings("unused")
public class EntityListener implements Listener {
    private LotusSurvival plugin;
    private Material witherHeartType;

    private static final Random random = new Random();

    public EntityListener(LotusSurvival plugin) {
        this.plugin = plugin;
        this.witherHeartType = Items.witherHeart.toItemStack().getType();
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent e) {
        LivingEntity entity = e.getEntity();
        Player killer = entity.getKiller();
        if (killer == null) {
            return;
        }

        SoulGem soulGem = SoulGem.getFromPlayerInventory(killer);
        if (soulGem != null) {
            if (random.nextInt(1, 20) <= 1) {
                int souls = LotusSurvival.getSoulsForEntityType(entity.getType());
                if (entity instanceof Zombie zombie) {
                    if (!zombie.isAdult()) {
                        souls = 10;
                    }
                }

                soulGem.setSouls(soulGem.getSouls() + souls);
                killer.getInventory().setItem(soulGem.getSlot(), soulGem.toItemStack());
                ColorUtils.coloredMessage(killer, "&fʏᴏᴜʀ ɢᴇᴍ ɪs ʙᴇɪɴɢ ғɪʟʟᴇᴅ ᴡɪᴛʜ &5sᴏᴜʟs&f...");
            } else {
                ColorUtils.coloredMessage(killer, "&fʏᴏᴜ &cғᴀɪʟᴇᴅ&f ᴛᴏ ɢᴀɪɴ sᴏᴜʟs ғʀᴏᴍ ᴀ &7&n" + entity.getType().name().toLowerCase().replaceAll("_", " "));
            }
        }

        World world = entity.getWorld();

        if (entity.getType() == EntityType.WITHER) {
            if (random.nextInt(0, 10) <= 1) {
                ColorUtils.coloredMessage(killer, "&7&osᴏᴍᴇᴛʜɪɴɢ ᴅʀᴏᴘᴘᴇᴅ ғʀᴏᴍ ᴛʜᴇ ᴡɪᴛʜᴇʀ.... ɪᴛ ʜᴜᴍs ᴡɪᴛʜ ɢʀᴇᴀᴛ ᴘᴏᴡᴇʀ...");
                world.dropItem(entity.getLocation(), Items.witherHeart.toItemStack());
                playSoundInRadius(entity, 5, Sound.ENTITY_ENDER_DRAGON_GROWL);
                world.spawnParticle(Particle.SOUL, entity.getLocation(), 100, 0, 1, 0, 0.04);
            }
        } else if (entity.getType() == EntityType.WARDEN) {
            if (random.nextInt(0, 100) <= 1) {
                ColorUtils.coloredMessage(killer, "&7&osᴏᴍᴇᴛʜɪɴɢ ᴅʀᴏᴘᴘᴇᴅ ғʀᴏᴍ ᴛʜᴇ ᴡᴀʀᴅᴇɴ... ɪᴛ sᴇᴇᴍs ᴛᴏ ʙᴇ ᴀ.. sᴏᴜʟ ɢᴇᴍ?");
                world.dropItem(entity.getLocation(), new SoulGem().toItemStack());
                playSoundInRadius(entity, 5, Sound.ENTITY_ENDER_DRAGON_GROWL);
                world.spawnParticle(Particle.SOUL, entity.getLocation(), 100, 0, 1, 0, 0.04);
                //TODO Unlock warden recipes
                //ColorUtils.coloredMessage(killer, "&7You have unlocked new recipes for Warden Armor and Tools");
            }
        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent e) {
        if (!(e.getEntity() instanceof Player player)) {
            return;
        }

        if (!Set.of(FIRE, FIRE_TICK, LAVA, WITHER).contains(e.getCause())) {
            return;
        }

        for (ItemStack itemStack : player.getInventory().getContents()) {
            if (itemStack.getType() == witherHeartType) {
                e.setCancelled(true);
                return;
            }
        }
    }

    @EventHandler
    public void onItemPickup(EntityPickupItemEvent e) {
        if (!(e.getEntity() instanceof Player player)) {
            return;
        }

        if (!Items.itemIdEquals(e.getItem().getItemStack(), "soul_gem")) {
            return;
        }

        SoulGem eventSoulGem = SoulGem.fromItemStack(e.getItem().getItemStack());

        PlayerInventory inv = player.getInventory();
        ItemStack[] contents = inv.getContents();
        for (int i = 0; i < contents.length; i++) {
            ItemStack itemStack = contents[i];
            if (Items.itemIdEquals(itemStack, "soul_gem")) {
                SoulGem invSoulGem = SoulGem.fromItemStack(itemStack);
                invSoulGem.setSouls(invSoulGem.getSouls() + eventSoulGem.getSouls());
                e.getItem().remove();
                e.setCancelled(true);
                inv.setItem(i, invSoulGem.toItemStack());
                return;
            }
        }
    }

    @SuppressWarnings("SameParameterValue")
    private void playSoundInRadius(Entity entity, int radius, Sound sound) {
        List<Entity> nearbyEntities = entity.getNearbyEntities(radius, radius, radius);
        for (Entity nearbyEntity : nearbyEntities) {
            if (nearbyEntity instanceof Player nbPlayer) {
                nbPlayer.playSound(entity.getLocation(), sound, 0.5f, 0.5f);
            }
        }
    }
}