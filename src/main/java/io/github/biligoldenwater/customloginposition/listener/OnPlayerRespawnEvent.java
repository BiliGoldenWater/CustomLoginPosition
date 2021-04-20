package io.github.biligoldenwater.customloginposition.listener;

import io.github.biligoldenwater.customloginposition.CustomLoginPosition;
import io.github.biligoldenwater.customloginposition.modules.GetLoginPosition;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.Plugin;

public class OnPlayerRespawnEvent implements Listener {
    @EventHandler
    public void onPlayerRespawnEvent(PlayerRespawnEvent e) {
        Plugin plugin = CustomLoginPosition.getInstance();
        FileConfiguration config = plugin.getConfig();

        if (!config.getBoolean("respawnAtLoginPosition")) return;

        Location loginLocation = GetLoginPosition.getLoginPosition(Bukkit.getServer(), e.getPlayer(), config);
        if (loginLocation != null) {
            e.setRespawnLocation(loginLocation);
        }
    }
}
