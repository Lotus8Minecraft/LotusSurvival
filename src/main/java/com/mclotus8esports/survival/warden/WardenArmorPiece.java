package com.mclotus8esports.survival.warden;

import com.mclotus8esports.survival.LotusSurvival;
import com.mclotus8esports.survival.SurvivalItem;
import com.stardevllc.starmclib.item.ItemBuilder;
import com.stardevllc.starmclib.item.enums.ArmorMaterial;
import com.stardevllc.starmclib.item.enums.ArmorSlot;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.trim.ArmorTrim;
import org.bukkit.inventory.meta.trim.TrimMaterial;
import org.bukkit.inventory.meta.trim.TrimPattern;

public class WardenArmorPiece extends SurvivalItem {
    
    private static final ArmorTrim armorTrim = new ArmorTrim(TrimMaterial.AMETHYST, TrimPattern.SILENCE);
    
    public WardenArmorPiece(ArmorSlot armorSlot, String effect) {
        super("warden_" + armorSlot.name().toLowerCase(), new ItemBuilder.Armor(ArmorMaterial.DIAMOND, armorSlot).trim(armorTrim)
                .displayName("&5ᴡᴀʀᴅᴇɴ ᴀʀᴍᴏʀ &8- &7" + LotusSurvival.armorSlotNames.get(armorSlot))
                .addEnchant(Enchantment.DURABILITY, 10)
                .addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 10)
                .unbreakable(true).addItemFlags(ItemFlag.HIDE_ARMOR_TRIM, ItemFlag.HIDE_UNBREAKABLE)
                .addLoreLine("&7&oғᴏʀɢᴇᴅ ɪɴ ᴛʜᴇ ᴅᴇᴘᴛʜs ᴏғ ᴛʜᴇ")
                .addLoreLine("&5&lᴠᴏɪᴅ ᴏғ sɪʟᴇɴᴄᴇ&7&o.")
                .addLoreLine("")
                .addLoreLine("&d&l&nᴇғғᴇᴄᴛs")
                .addLoreLine(effect)
                .addLoreLine("&d&l&nғᴜʟʟ sᴇᴛ ᴇғғᴇᴄᴛs")
                .addLoreLine("&8- &7ᴜsᴇ sᴏɴɪᴄ ᴀᴛᴛᴀᴄᴋ &8[&a120 &fsᴇᴄᴏɴᴅ ᴄᴏᴏʟᴅᴏᴡɴ&8]")
                .addLoreLine("&8- &7ɪᴍᴍᴜɴᴇ ᴛᴏ ᴛʜᴇ ᴅᴀʀᴋɴᴇss ᴇғғᴇᴄᴛ")
                .addLoreLine("&8- &7ɪᴍᴍᴜɴᴇ ᴛᴏ sᴋᴜʟᴋ sᴇɴsᴏʀs"));
    }
}
