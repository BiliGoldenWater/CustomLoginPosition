package io.github.biligoldenwater.customloginposition.listener;

import io.github.biligoldenwater.customloginposition.modules.GetLoginPosition;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class OnPlayerRespawnEvent implements Listener {
    @EventHandler
    public void onPlayerRespawnEvent(PlayerRespawnEvent e){
        Location loginLocation = GetLoginPosition.getLoginPosition(Bukkit.getServer(),e.getPlayer(),Bukkit.getServer().getPluginManager().getPlugin("CustomLoginPosition").getConfig());
        if(loginLocation!=null){
            e.setRespawnLocation(loginLocation);
        }
    }
}
