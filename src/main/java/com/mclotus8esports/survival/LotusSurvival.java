package com.mclotus8esports.survival;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.mclotus8esports.survival.items.SoulGem;
import com.mclotus8esports.survival.listeners.BlockListener;
import com.mclotus8esports.survival.listeners.EntityListener;
import com.stardevllc.staritems.CustomItem;
import com.stardevllc.staritems.CustomItemsManager;
import com.stardevllc.staritems.ItemKey;
import com.stardevllc.starmclib.item.enums.ArmorSlot;
import com.stardevllc.starmclib.item.enums.ToolType;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.EnumMap;
import java.util.Map;
import java.util.Set;

public class LotusSurvival extends JavaPlugin {

    private CustomItemsManager customItemsManager;

    public static final Map<ArmorSlot, String> armorSlotNames = new EnumMap<>(ArmorSlot.class) {{
        put(ArmorSlot.HELMET, "ʜᴇʟᴍ");
        put(ArmorSlot.CHESTPLATE, "ᴄʜᴇsᴛᴘʟᴀᴛᴇ");
        put(ArmorSlot.LEGGINGS, "ʟᴇɢɢɪɴɢs");
        put(ArmorSlot.BOOTS, "ʙᴏᴏᴛs");
    }};
    
    public static final Map<ToolType, String> toolTypeNames = new EnumMap<>(ToolType.class) {{
        put(ToolType.SWORD, "sᴡᴏʀᴅ");
        put(ToolType.PICKAXE, "ᴘɪᴄᴋᴀxᴇ");
        put(ToolType.AXE, "ᴀxᴇ");
        put(ToolType.SHOVEL, "sʜᴏᴠᴇʟ");
        put(ToolType.HOE, "ʜᴏᴇ");
    }};
    
    public static final BiMap<Integer, Set<EntityType>> soulsPerEntity = HashBiMap.create();
    
    static {
        soulsPerEntity.put(100, Set.of(EntityType.PLAYER));
        soulsPerEntity.put(50, Set.of(EntityType.WARDEN, EntityType.ENDER_DRAGON, EntityType.ELDER_GUARDIAN));
        soulsPerEntity.put(25, Set.of(EntityType.WITHER_SKELETON, EntityType.GHAST));
        soulsPerEntity.put(10, Set.of(EntityType.VEX));
        soulsPerEntity.put(5, Set.of(EntityType.ENDERMAN, EntityType.SHULKER, EntityType.HUSK, EntityType.PHANTOM));
    }
    
    public void onEnable() {
        customItemsManager = getServer().getServicesManager().getRegistration(CustomItemsManager.class).getProvider();
        
        for (Field field : Items.class.getDeclaredFields()) {
            if (CustomItem.class.isAssignableFrom(field.getType())) {
                try {
                    customItemsManager.addCustomItem((CustomItem) field.get(null));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        
        customItemsManager.addCustomItemClass(new ItemKey("lotussurvival", "soul_gem"), SoulGem.class);
        
        getServer().getPluginManager().registerEvents(new BlockListener(this), this);
        getServer().getPluginManager().registerEvents(new EntityListener(this), this);
    }
    
    public static int getSoulsForEntityType(EntityType entityType) {
        for (Map.Entry<Integer, Set<EntityType>> entry : soulsPerEntity.entrySet()) {
            if (entry.getValue().contains(entityType)) {
                return entry.getKey();
            }
        }
        
        return 1;
    }
}