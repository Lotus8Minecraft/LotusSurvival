package com.mclotus8esports.survival.items;

import com.cryptomorin.xseries.XMaterial;
import com.mclotus8esports.survival.SurvivalItem;
import com.stardevllc.staritems.CustomItem;
import com.stardevllc.staritems.ItemKey;
import com.stardevllc.starmclib.item.ItemBuilder;
import de.tr7zw.nbtapi.NBT;
import de.tr7zw.nbtapi.iface.ReadWriteNBT;
import de.tr7zw.nbtapi.iface.ReadableNBT;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class SoulGem extends SurvivalItem {
    
    private int souls, slot;
    
    public SoulGem(int souls) {
        super("soul_gem", ItemBuilder.of(XMaterial.ECHO_SHARD).displayName("&5sᴏᴜʟ ɢᴇᴍ")
                .addLoreLine("&cᴋɪʟʟ ᴍᴏʙs &fᴏʀ &aᴘʟᴀʏᴇʀs&f ᴛᴏ ғɪʟʟ ᴛʜɪs ᴡɪᴛʜ sᴏᴜʟs.")
                .addLoreLine("")
                .addLoreLine("&fᴜsᴇᴅ ᴛᴏ ᴄʀᴀғᴛ &5&nᴘʀɪᴍᴇᴅ ᴡᴀʀᴅᴇɴ ɪɴɢᴏᴛs&f ᴀɴᴅ ᴛᴏ ᴄʀᴇᴀᴛᴇ ᴀʀᴍᴏʀ")
                .addLoreLine("&fᴀɴᴅ ᴛᴏᴏʟs ʟɪᴋᴇ ᴛʜᴇ &5ᴡᴀʀᴅᴇɴ ᴀʀᴍᴏʀ sᴇᴛ/ᴛᴏᴏʟs")
                .addLoreLine("&5sᴏᴜʟs: &7&n" + souls + " sᴏᴜʟs")
                .addLoreLine("")
                .addLoreLine("&5ʀᴀʀɪᴛʏ: &bʀᴀʀᴇ"));
        this.souls = souls;
    }
    
    public SoulGem() {
        this(0);
    }
    
    public static SoulGem fromItemStack(ItemStack itemStack) {
        ItemKey itemKey = CustomItem.getKeyFromItem(itemStack);
        if (!itemKey.getKey().equals("soul_gem")) {
            return null;
        }
        
        int souls = NBT.get(itemStack, nbt -> {
            ReadableNBT lotusNBT = nbt.getCompound("lotussurvival");
            if (lotusNBT == null) {
                return null;
            }
            
            return lotusNBT.getInteger("souls");
        });
        
        return new SoulGem(souls);
    }
    
    public static SoulGem getFromPlayerInventory(Player player) {
        PlayerInventory inv = player.getInventory();
        for (int i = 0; i < inv.getContents().length; i++) {
            ItemStack itemStack = inv.getContents()[i];
            SoulGem soulGem = fromItemStack(itemStack);
            if (soulGem != null) {
                soulGem.setSlot(i);
                return soulGem;
            }
        }
        
        return null;
    }

    @Override
    public void applyCustomNBT(ItemStack itemStack) {
        NBT.modify(itemStack, nbt -> {
            ReadWriteNBT lotusCompound = nbt.getOrCreateCompound("lotussurvival");
            lotusCompound.setInteger("souls", getSouls());
        });
    }

    public int getSouls() {
        return souls;
    }

    public void setSouls(int souls) {
        this.souls = souls;
        getItemBuilder().setLoreLine(4, "&5sᴏᴜʟs: &7&n" + souls + " sᴏᴜʟs");
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }
}
