package com.minecraftport.giftToken;

import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class tokenListener implements Listener {

    public static main plugin;

    public tokenListener(main instance) {
        plugin = instance;
    }

    @EventHandler
    public void onPlayerDrink(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        Location loc = p.getLocation();
        ItemStack token = p.getItemInHand();

        if (token != null && token.getAmount() != 0 && token.getType() == Material.GOLD_NUGGET && token.getItemMeta().getLore().contains(ChatColor.BLUE + "Right-clicking with this token")) {

            //Check inventory for free space
            int space = 0;
            for (ItemStack content : p.getInventory().getContents()) {
                if (content == null) {
                    space++;
                }
            }

            if (space > 0) {
                if (p.getItemInHand().getAmount() > 1) {
                    p.getItemInHand().setAmount(p.getItemInHand().getAmount() - 1);
                } else if (p.getItemInHand().getAmount() == 1) {
                    p.getInventory().removeItem(p.getInventory().getItemInHand());
                }

                p.playSound(loc, Sound.ITEM_PICKUP, 1, 0);
                p.playEffect(loc, Effect.SMOKE, 0);


                //For itemstacks: Create ItemStack, Add enchants, set display name, add lore, set lore, set meta, add to inv, update inv
                double randNumber = Math.round(Math.random() * 50);
                if (randNumber == 1) {
                    ItemStack is = new ItemStack(Material.DIAMOND_SWORD, 1);
                    is.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 80);
                    is.addUnsafeEnchantment(Enchantment.LOOT_BONUS_MOBS, 100);
                    ItemMeta im = is.getItemMeta();
                    im.setDisplayName(ChatColor.GOLD + "Sword of Loot");
                    List<String> lore = new ArrayList<String>();
                    lore.add(ChatColor.RED + "This is some");
                    lore.add(ChatColor.BLUE + "test lore!");
                    im.setLore(lore);
                    is.setItemMeta(im);
                    p.getInventory().addItem(is);
                    p.updateInventory();
                } else if (randNumber > 2 && randNumber < 20) {
                    ItemStack is = new ItemStack(Material.DIAMOND, 10);
                    p.getInventory().addItem(is);
                    p.updateInventory();
                } else if (randNumber == 21) {
                    ItemStack is = new ItemStack(Material.DIAMOND_PICKAXE, 1);
                    is.setDurability((short) (is.getDurability() + 1558 + (Math.random() * 2)));
                    is.addUnsafeEnchantment(Enchantment.DIG_SPEED, 10);
                    is.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 100);
                    ItemMeta im = is.getItemMeta();
                    im.setDisplayName(ChatColor.GOLD + "The God Pick");
                    List<String> lore = new ArrayList<String>();
                    lore.add(ChatColor.GOLD + "Seems to have just a few good");
                    lore.add(ChatColor.GOLD + "swings left in it..");
                    im.setLore(lore);
                    is.setItemMeta(im);
                    p.getInventory().addItem(is);
                    p.updateInventory();
                } else {
                    ItemStack is = new ItemStack(Material.APPLE, 1);
                    ItemMeta im = is.getItemMeta();
                    im.setLore(Arrays.asList("This might look like an apple...", "But it is probably also a worm's home.."));
                    im.setDisplayName("" + ChatColor.GOLD + "Ripe Apple");
                    is.setItemMeta(im);
                    p.getInventory().addItem(is);
                    p.updateInventory();
                }
            } else {
                p.sendMessage(main.tag + ChatColor.DARK_PURPLE + "Make sure at least one inventory slot is free!");
            }
        }
    }
}
