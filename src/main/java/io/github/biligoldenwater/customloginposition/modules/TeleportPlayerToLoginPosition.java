package io.github.biligoldenwater.customloginposition.modules;

import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;

public class TeleportPlayerToLoginPosition {
    public static void teleportPlayerToLoginPosition(Server server, Player player, Configuration config) {
        Location loginLocation = GetLoginPosition.getLoginPosition(server, player, config);

        if (loginLocation != null) {
            player.teleport(loginLocation);
        }
    }
}
