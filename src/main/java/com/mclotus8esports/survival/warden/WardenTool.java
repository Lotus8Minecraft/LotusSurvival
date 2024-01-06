package com.mclotus8esports.survival.warden;

import com.cryptomorin.xseries.XMaterial;
import com.mclotus8esports.survival.LotusSurvival;
import com.mclotus8esports.survival.SurvivalItem;
import com.stardevllc.starmclib.item.ItemBuilder;
import com.stardevllc.starmclib.item.enums.ToolType;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;

public class WardenTool extends SurvivalItem {
    public WardenTool(ToolType toolType, String... effects) {
        super("warden_" + toolType.name().toLowerCase(), ItemBuilder.of(XMaterial.valueOf("DIAMOND_" + toolType.name()))
                .displayName("&5ᴡᴀʀᴅᴇɴ &b" + LotusSurvival.toolTypeNames.get(toolType))
                .addEnchant(Enchantment.DURABILITY, 10)
                .addEnchant(toolType == ToolType.SWORD ? Enchantment.DAMAGE_ALL : Enchantment.DIG_SPEED, 10)
                .unbreakable(true).addItemFlags(ItemFlag.HIDE_UNBREAKABLE)
                .addLoreLine("&7&oғᴏʀɢᴇᴅ ɪɴ ᴛʜᴇ ᴅᴇᴘᴛʜs ᴏғ ᴛʜᴇ")
                .addLoreLine("&5&lᴠᴏɪᴅ ᴏғ sɪʟᴇɴᴄᴇ&7&o. ᴛʜᴇ ᴘᴏᴡᴇʀ ᴏғ")
                .addLoreLine("&7&oᴛʜᴇ &5ᴠᴏɪᴅ&7&o ʜᴜᴍs ᴡɪᴛʜɪɴ ᴛʜɪs")
                .addLoreLine("&7&oɪᴛᴇᴍ ᴀɴᴅ ᴘʀᴏᴠɪᴅᴇs ʏᴏᴜ ᴘᴏᴡᴇʀ."));
        if (effects != null) {
            getItemBuilder().addLoreLine("").addLoreLine("&d&l&nᴇғғᴇᴄᴛs");
            for (String effect : effects) {
                getItemBuilder().addLoreLine(effect);
            }
        }
    }
}
