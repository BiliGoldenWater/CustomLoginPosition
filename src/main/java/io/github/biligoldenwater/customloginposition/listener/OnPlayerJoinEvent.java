package io.github.biligoldenwater.customloginposition.listener;

import io.github.biligoldenwater.customloginposition.modules.CheckPermissions;
import io.github.biligoldenwater.customloginposition.modules.TeleportPlayerToLoginPosition;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class OnPlayerJoinEvent implements Listener {
    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event){
        if(CheckPermissions.hasPermissions(event.getPlayer(),"customloginposition.bypass.fixedloginposition")){
            return;
        }

        Server server = Bukkit.getServer();
        Plugin plugin = server.getPluginManager().getPlugin("CustomLoginPosition");
        FileConfiguration config = plugin.getConfig();

        BukkitRunnable teleportPlayerToLoginPosition = new BukkitRunnable() {
            @Override
            public void run() {
                TeleportPlayerToLoginPosition.teleportPlayerToLoginPosition(server,event.getPlayer(),config);
            }
        };

        teleportPlayerToLoginPosition.runTaskLater(plugin,10);
    }
}
