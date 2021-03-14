package io.github.biligoldenwater.customloginposition.commands;

import io.github.biligoldenwater.customloginposition.modules.CheckPermissions;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class TabCustomLoginPosition {
    JavaPlugin plugin;
    Server server;
    public TabCustomLoginPosition(JavaPlugin plugin){
        this.plugin = plugin;
        this.server = plugin.getServer();
        this.plugin.getCommand("customloginposition").setTabCompleter(customloginposition_tab);
    }

    TabCompleter customloginposition_tab = new TabCompleter() {
        @Override
        public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
            List<String> completions = new ArrayList<>();
            switch (args.length){
                case 1:
                    if(CheckPermissions.hasPermissions(sender,"customloginposition.command.help") && "help".startsWith(args[0]))completions.add("help");
                    if(CheckPermissions.hasPermissions(sender,"customloginposition.command.setloginposition") && "setloginposition".startsWith(args[0]))completions.add("setloginposition");
                    if(CheckPermissions.hasPermissions(sender,"customloginposition.command.banworld") && "banworld".startsWith(args[0]))completions.add("banworld");
                    if(CheckPermissions.hasPermissions(sender,"customloginposition.command.reload") && "reload".startsWith(args[0]))completions.add("reload");
                    return completions;
                case 2:
                    switch (args[0]){
                        case "banworld":
                            if(CheckPermissions.hasPermissions(sender,"customloginposition.command.banworld.add") && "add".startsWith(args[1]))completions.add("add");
                            if(CheckPermissions.hasPermissions(sender,"customloginposition.command.banworld.remove") && "reload".startsWith(args[0]))completions.add("remove");
                            return completions;
                    }
            }

            return new ArrayList<>();
        }
    };

}
