package org.thanhnguyen.thanhplugin;

import org.bukkit.plugin.java.JavaPlugin;

public final class ThanhPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        getCommand("home").setExecutor(new SetHomeCommand(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
