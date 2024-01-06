package com.mclotus8esports.survival;

import com.cryptomorin.xseries.XMaterial;
import com.mclotus8esports.survival.warden.WardenArmorPiece;
import com.mclotus8esports.survival.warden.WardenTool;
import com.stardevllc.starmclib.item.ItemBuilder;
import com.stardevllc.starmclib.item.enums.ArmorSlot;
import com.stardevllc.starmclib.item.enums.ToolType;
import de.tr7zw.nbtapi.NBT;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public final class Items {
    public static SurvivalItem witherHeart = new SurvivalItem("wither_heart", ItemBuilder.of(XMaterial.MAGMA_CREAM).displayName("&8ᴡɪᴛʜᴇʀ's &4ʜᴇᴀʀᴛ").addLoreLine("").addLoreLine("&aᴘʀᴏᴛᴇᴄᴛs&f ʏᴏᴜ ғʀᴏᴍ ᴀʟʟ &cғɪʀᴇ&f ᴀɴᴅ &8ᴡɪᴛʜᴇʀ&f ᴅᴀᴍᴀɢᴇ")); 
    public static SurvivalItem wardenCrystal = new SurvivalItem("warden_crystal", ItemBuilder.of(XMaterial.DIAMOND).displayName("&5ᴡᴀʀᴅᴇɴ ᴄʀʏᴛsᴛᴀʟ"));
    public static SurvivalItem primedWardenCrystal = new SurvivalItem("primed_warden_crystal", ItemBuilder.of(XMaterial.DIAMOND).displayName("&5ᴡᴀʀᴅᴇɴ ᴄʀʏᴛsᴛᴀʟ &8[&f&lᴘʀɪᴍᴇᴅ&8]"));
    public static SurvivalItem villagerTear = new SurvivalItem("villager_tear", ItemBuilder.of(XMaterial.GHAST_TEAR).displayName("&4ᴠɪʟʟᴀɢᴇʀ ᴛᴇᴀʀ"));
    public static SurvivalItem wardenHelmet = new WardenArmorPiece(ArmorSlot.HELMET, "&8- &7ᴀʟʟᴏᴡs ʏᴏᴜ ᴛᴏ sᴇᴇ ɪɴ ᴛʜᴇ ᴅᴀʀᴋ");
    public static SurvivalItem wardenChestplate = new WardenArmorPiece(ArmorSlot.CHESTPLATE, "&8- &7ɢɪᴠᴇs ʏᴏᴜ &csᴛʀᴇɴɢᴛʜ &73");
    public static SurvivalItem wardenLeggings = new WardenArmorPiece(ArmorSlot.LEGGINGS, "&8- &7ɢɪᴠᴇs ʏᴏᴜ &5sᴏᴜʟ sᴘᴇᴇᴅ");
    public static SurvivalItem wardenBoots = new WardenArmorPiece(ArmorSlot.BOOTS, "&8- &7ɢɪᴠᴇs ʏᴏᴜ &asᴘᴇᴇᴅ &73");
    public static SurvivalItem wardenSword = new WardenTool(ToolType.SWORD, "&8- &a+8 &cᴅᴀᴍᴀɢᴇ&7 ᴏɴ ʟᴏᴡ ʜᴇᴀʟᴛʜ", "&8- &a+10 &cᴅᴀᴍᴀɢᴇ&7 ᴛᴏ ʙᴏssᴇs", "&8- &7ᴀᴘᴘʟʏ &f&lɢʟᴏᴡ ᴇғғᴇᴄᴛ&7 ᴛᴏ ᴅᴀᴍᴀɢᴇᴅ ᴛᴀʀɢᴇᴛs &8[&fʟᴀsᴛs &7200&f sᴇᴄᴏɴᴅs&8]");
    public static SurvivalItem wardenPickaxe = new WardenTool(ToolType.PICKAXE, "&8- &7ᴍɪɴᴇs ғᴀsᴛᴇʀ ɪɴ ᴅᴇᴇᴘsʟᴀᴛᴇ", "&8- &a10%%&7 ᴄʜᴀɴᴄᴇ ᴛᴏ &eғᴇᴇᴅ&7 ʏᴏᴜ");
    public static SurvivalItem wardenAxe = new WardenTool(ToolType.AXE, "&8- &7ᴇxᴛʀᴀ &cᴅᴀɴᴀɢᴇ&7 ᴛᴏ sʟᴏᴡᴇᴅ ᴇɴᴛɪᴛɪᴇs", "&8- &a12%% &7ᴄʜᴀɴᴄᴇ ᴛᴏ &8sʟᴏᴡ ᴇɴᴛɪᴛɪᴇs");
    public static SurvivalItem wardenShovel = new WardenTool(ToolType.SHOVEL);
    public static SurvivalItem wardenHoe = new WardenTool(ToolType.HOE, "&8- &7ʀᴇᴘʟᴀɴᴛs ᴄʀᴏᴘs ᴜᴘᴏɴ ʜᴀʀᴠᴇsᴛɪɴɢ");

    public static boolean itemIdEquals(ItemStack itemStack, String value) {
        if (itemStack == null || itemStack.getType() == Material.AIR) {
            return false;
        }

        String tagValue = NBT.get(itemStack, nbt -> nbt.getString("staritems"));
        if (tagValue == null || tagValue.isEmpty()) {
            return false;
        }
        return tagValue.equalsIgnoreCase(value);
    }
}