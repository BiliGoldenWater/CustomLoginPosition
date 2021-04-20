package io.github.biligoldenwater.customloginposition.modules;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;

import java.util.List;

public class GetLoginPosition {
    public static Location getLoginPosition(Server server, Player player, Configuration config){
        List<World> worlds = server.getWorlds();
        String worldName = config.getString("loginWorld");
        World loginWorld = null;
        boolean worldExist = false;

        for(World i : worlds){
            if(i.getName().equals(worldName)){
                worldExist = true;
                loginWorld = i;
                break;
            }
        }

        if(!worldExist){
            server.getPluginManager().getPlugin("CustomLoginPosition").getLogger().warning("CustomLoginPosition Login world not exist.");
            return null;
        }
        else if(CheckWorldIsBaned.checkWorldIsBaned(player,server.getWorld(worldName),config)){
            server.getPluginManager().getPlugin("CustomLoginPosition").getLogger().warning("CustomLoginPosition Login world is a baned world for "+player.getName()+".");
            return null;
        }

        double loginX=config.getDouble("loginPositionX"),
                loginY=config.getDouble("loginPositionY"),
                loginZ=config.getDouble("loginPositionZ");
        float loginYaw=(float) config.getDouble("loginPositionYaw"),
                loginPitch=(float) config.getDouble("loginPositionPitch");
        Location loginLocation = new Location(loginWorld,loginX,loginY,loginZ,loginYaw,loginPitch);

        for (int i=1;i<=loginLocation.getY();++i){
            if(server.getWorld(worldName).getBlockAt((int)loginLocation.getX(),(int)loginLocation.getY() - i,(int)loginLocation.getZ()).getType() != Material.AIR){
                loginLocation.setY((int) loginLocation.getY() - i + 1);
                break;
            }
        }

        return loginLocation;
    }
}
