package com.minecraftport.giftToken;


import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public class main extends JavaPlugin{

    public static main plugin;
    public final tokenListener tl = new tokenListener(this);
    public static String tag = ChatColor.GOLD + "[gToken]"+ ChatColor.RESET + " ";

    @Override
    public void onDisable(){

    }
    @Override
    public void onEnable(){
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(this.tl, this);
        new tokenListener(this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
        Player p = (Player) sender;
        if(commandLabel.equalsIgnoreCase("gtoken") && p.isOp()){//Change this to separate if statement if it doesn't work!
            ItemStack token = new ItemStack(Material.GOLD_NUGGET, 10);
            ItemMeta im = token.getItemMeta();
            im.setLore(Arrays.asList("" + ChatColor.BLUE + "Right-clicking with this token", "" + ChatColor.BLUE + "will give you a " + ChatColor.GOLD + "gift!"));
            im.setDisplayName("" + ChatColor.GOLD + "Gift Token");
            token.setItemMeta(im);
            p.getInventory().addItem(token);
        }
        return true;
    }

}