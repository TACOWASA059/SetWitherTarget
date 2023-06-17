package com.github.TACOWASA059.SetWitherTarget;

import com.github.TACOWASA059.SetWitherTarget.commands.PluginCommand;
import com.github.TACOWASA059.SetWitherTarget.commands.PluginCommandCompleter;
import com.github.TACOWASA059.SetWitherTarget.event.EntityEvent;
import com.github.TACOWASA059.SetWitherTarget.scoreboard.saveWitherUUID;
import org.bukkit.plugin.java.JavaPlugin;

public final class SetWitherTarget extends JavaPlugin {
    public static SetWitherTarget plugin;
    public saveWitherUUID saveWitherUUID;


    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin=this;
        saveWitherUUID=new saveWitherUUID();
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new EntityEvent(),this);

        getCommand("WitherCommand").setExecutor(new PluginCommand());
        getCommand("WitherCommand").setTabCompleter(new PluginCommandCompleter());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
