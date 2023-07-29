package org.thanhnguyen.thanhplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class SetHomeCommand implements CommandExecutor {

    ThanhPlugin plugin;

    public SetHomeCommand(ThanhPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public  boolean onCommand(CommandSender sender, Command command,String label, String[] args){
        if(sender instanceof Player){
            Player player = (Player) sender;

            if(plugin.getConfig().getBoolean("enable") ){
                if(args.length ==1 && args[0].equalsIgnoreCase("set")){
                    if(plugin.getConfig().isConfigurationSection("savedlocations"+player.getName())){
                        player.sendMessage(ChatColor.GREEN+"Overrided current location at:"+ ChatColor.GRAY+plugin.getConfig().getDouble("savedlocations."+player.getName()+".x"+
                                plugin.getConfig().getDouble("savedlocations."+player.getName()+".y"+
                                        plugin.getConfig().getDouble("savedlocations."+player.getName()+".z"))));
                      saveLocation(player);
                    }else {
                        saveLocation(player);
                    }
                } else if (args.length ==1 && args[0].equalsIgnoreCase("return")) {
                    if(plugin.getConfig().isConfigurationSection("savedlocation."+player.getName())){
                        Location return_location= new Location(player.getWorld(),plugin.getConfig().getDouble("savedlocations."+player.getName()+".x"),
                                plugin.getConfig().getDouble("savedlocations."+player.getName()+".y"),
                                        plugin.getConfig().getDouble("savedlocations."+player.getName()+".z"));
                        player.teleport(return_location);
                        player.sendMessage(ChatColor.GREEN+"ve nha roi nha cu");
                        plugin.getConfig().set("savedlocations"+player.getName(),null);
                        plugin.saveConfig();
                    }else{
                        player.sendMessage(ChatColor.RED+"Chua sethome ma return cai deo gi?");
                    }
                } else if (args.length ==1 &args[0].equalsIgnoreCase("reload")) {
                    plugin.reloadConfig();
                    player.sendMessage(ChatColor.GREEN+"Reload xong r nhe");
                }
//                if(args.length ==1 && args[0].equalsIgnoreCase("eggs")){
//                    player.setLevel(1000);
//                    player.getAllowFlight();
//                    Inventory inventory = Bukkit.createInventory(player, 30);
//                    ItemStack item = new ItemStack(Material.DIAMOND_BLOCK,64);
//                    inventory.setItem(0,item);
//                    player.openInventory(inventory);
//                }
            }
        }
        return true;
    }
    public void saveLocation(Player player){
        Location first = player.getLocation();
        plugin.getConfig().createSection("savedlocations."+player.getName());
        plugin.getConfig().set("savedlocations."+player.getName() + ".x",first.getX());
        plugin.getConfig().set("savedlocations."+player.getName() + ".y",first.getY());
        plugin.getConfig().set("savedlocations."+player.getName() + ".z",first.getZ());
        plugin.saveConfig();
        player.sendMessage(ChatColor.GREEN+"set home success" + ChatColor.RED + plugin.getConfig().getDouble("savedlocations."+player.getName()+".x"+
                plugin.getConfig().getDouble("savedlocations."+player.getName()+".y"+
                        plugin.getConfig().getDouble("savedlocations."+player.getName()+".z"))));
    }
}
