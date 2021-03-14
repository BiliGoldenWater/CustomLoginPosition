package io.github.biligoldenwater.customloginposition.commands;

import io.github.biligoldenwater.customloginposition.modules.CheckPermissions;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class CommandCustomLoginPosition {
    JavaPlugin plugin;
    Server server;
    public CommandCustomLoginPosition(JavaPlugin plugin){
        this.plugin = plugin;
        this.server = plugin.getServer();
        this.plugin.getCommand("customloginposition").setExecutor(customloginposition_command);
    }

    CommandExecutor customloginposition_command = new CommandExecutor() {
        @Override
        public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
            switch (args.length){
                case 1:
                    switch (args[0]){
                        case "help":
                            if(!CheckPermissions.hasPermissions(sender,"customloginposition.command.help")){
                                sender.sendMessage("§cYou don't have permission to use this command.");
                                return true;
                            }
                            sender.sendMessage("CustomLoginPosition 1.0-SNAPSHOT by.Golden_Water");
                            sender.sendMessage("Usage:/clp help (Show this message)");
                            sender.sendMessage("Usage:/clp setloginposition (Set your position to login position)");
                            sender.sendMessage("Usage:/clp banworld <add|remove> (Add or remove your world to banworld list)");
                            sender.sendMessage("Usage:/clp reload (Reload config)");
                            return true;
                        case "setloginposition":
                            if (!(sender instanceof Player)) {
                                sender.sendMessage("§cOnly player can use this command!");
                                return true;
                            }
                            if(!CheckPermissions.hasPermissions(sender,"customloginposition.command.setloginposition")){
                                sender.sendMessage("§cYou don't have permission to use this command.");
                                return true;
                            }
                            Player player = (Player) sender;
                            Configuration config = plugin.getConfig();
                            Location location = player.getLocation();
                            double playerX=location.getX(),
                                    playerY=location.getY(),
                                    playerZ=location.getZ();
                            float playerYaw=location.getYaw(),
                                    playerPitch=location.getPitch();
                            config.set("loginWorld",player.getWorld().getName());
                            config.set("loginPositionX",playerX);
                            config.set("loginPositionY",playerY);
                            config.set("loginPositionZ",playerZ);
                            config.set("loginPositionYaw",playerYaw);
                            config.set("loginPositionPitch",playerPitch);
                            sender.sendMessage("Success to set your position to login position");
                            sender.sendMessage("X:§b "+playerX+"§r ,Y:§b "+playerY+"§r ,Z:§b "+playerZ+"§r ,Yaw:§b "+playerYaw+"§r ,Pitch:§b "+playerPitch+".");

                            plugin.saveConfig();

                            return true;
                        case "banworld":
                            if(!CheckPermissions.hasPermissions(sender,"customloginposition.command.banworld")){
                                sender.sendMessage("§cYou don't have permission to use this command.");
                                return true;
                            }

                            sender.sendMessage("Usage:/clp banworld <add|remove> (Add or remove your world to banworld list)");

                            return true;
                        case "reload":
                            if(!CheckPermissions.hasPermissions(sender,"customloginposition.command.reload")){
                                sender.sendMessage("§cYou don't have permission to use this command.");
                                return true;
                            }

                            plugin.reloadConfig();
                            sender.sendMessage("Reloaded config.");

                            return true;
                    }
                case 2:
                    switch (args[0]){
                        case "banworld":
                            if (!(sender instanceof Player)) {
                                sender.sendMessage("§cOnly player can use this command!");
                                return true;
                            }
                            if(!CheckPermissions.hasPermissions(sender,"customloginposition.command.banworld")){
                                sender.sendMessage("§cYou don't have permission to use this command.");
                                return true;
                            }

                            Player player = (Player) sender;
                            Configuration config = plugin.getConfig();
                            List<String> banWorldList =  config.getStringList("banWorld");

                            switch (args[1]){
                                case "add":
                                    if(!CheckPermissions.hasPermissions(sender,"customloginposition.command.banworld.add")){
                                        sender.sendMessage("§cYou don't have permission to use this command.");
                                        return true;
                                    }

                                    boolean isInBanWorldList = false;
                                    for(String i : banWorldList){
                                        if(i.equals(player.getWorld().getName())){
                                            isInBanWorldList = true;
                                        }
                                    }

                                    if(!isInBanWorldList){
                                        banWorldList.add(player.getWorld().getName());
                                        config.set("banWorld",banWorldList);
                                        plugin.saveConfig();
                                        sender.sendMessage("Added "+player.getWorld().getName()+" to ban world list.");
                                    }
                                    else{
                                        sender.sendMessage(player.getWorld().getName()+" are already in ban world list.");
                                    }

                                    break;
                                case "remove":
                                    if(!CheckPermissions.hasPermissions(sender,"customloginposition.command.remove")){
                                        sender.sendMessage("§cYou don't have permission to use this command.");
                                        return true;
                                    }
                                    boolean isRemoved = banWorldList.remove(player.getWorld().getName());
                                    config.set("banWorld",banWorldList);
                                    plugin.saveConfig();
                                    if (isRemoved) {
                                        sender.sendMessage("Removed " + player.getWorld().getName() + " in ban world list.");
                                    } else {
                                        sender.sendMessage("Can't find " + player.getWorld().getName() + " in ban world list.");
                                    }
                                    break;
                                default:
                                    sender.sendMessage("Usage:/clp banworld <add|remove> (Add or remove your world to banworld list)");
                                    break;
                            }

                            return true;

                    }
                default:
                    return false;
            }
        }
    };
}
