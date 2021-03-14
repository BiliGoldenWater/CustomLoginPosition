package io.github.biligoldenwater.customloginposition;

import io.github.biligoldenwater.customloginposition.commands.CommandCustomLoginPosition;
import io.github.biligoldenwater.customloginposition.commands.TabCustomLoginPosition;
import io.github.biligoldenwater.customloginposition.listener.OnPlayerJoinEvent;
import io.github.biligoldenwater.customloginposition.listener.OnPlayerMoveEvent;
import io.github.biligoldenwater.customloginposition.listener.OnPlayerRespawnEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class CustomLoginPosition extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();

        getServer().getPluginManager().registerEvents(new OnPlayerJoinEvent(),this);
        getServer().getPluginManager().registerEvents(new OnPlayerMoveEvent(),this);
        getServer().getPluginManager().registerEvents(new OnPlayerRespawnEvent(),this);

        new CommandCustomLoginPosition(this);
        new TabCustomLoginPosition(this);

        getLogger().info("CustomLoginPosition Enabled");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        saveConfig();
        getLogger().info("CustomLoginPosition Disabled");
    }
}
