package io.github.biligoldenwater.customloginposition.modules;

import org.bukkit.World;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;

public class CheckWorldIsBaned {
    public static boolean checkWorldIsBaned(Player player, World world, Configuration config){
        if(CheckPermissions.hasPermissions(player.getPlayer(),"customloginposition.bypass.banworld."+world.getName())){
            return false;
        }

        boolean worldIsBan = false;

        if(!config.getBoolean("enableBanWorld"))return false;

        for(String i : config.getStringList("banWorld")){
            if(world.getName().equals(i)){
                worldIsBan = true;
            }
        }

        return worldIsBan;
    }
}
