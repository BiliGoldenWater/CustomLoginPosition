package io.github.biligoldenwater.customloginposition.listener;

import io.github.biligoldenwater.customloginposition.modules.CheckWorldIsBaned;
import io.github.biligoldenwater.customloginposition.modules.TeleportPlayerToLoginPosition;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;

public class OnPlayerMoveEvent implements Listener {
    @EventHandler
    public void onPlayerMoveEvent(PlayerMoveEvent event){
        Server server = Bukkit.getServer();
        Plugin plugin = server.getPluginManager().getPlugin("CustomLoginPosition");
        FileConfiguration config = plugin.getConfig();

        boolean worldIsBaned = CheckWorldIsBaned.checkWorldIsBaned(event.getPlayer(), event.getPlayer().getWorld(),config);

        if(!worldIsBaned)return;

        TeleportPlayerToLoginPosition.teleportPlayerToLoginPosition(server,event.getPlayer(),config);
    }
}
