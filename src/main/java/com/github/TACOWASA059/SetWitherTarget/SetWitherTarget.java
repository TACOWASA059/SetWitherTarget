package com.github.TACOWASA059.SetWitherTarget;

import com.github.TACOWASA059.SetWitherTarget.commands.PluginCommand;
import com.github.TACOWASA059.SetWitherTarget.commands.PluginCommandCompleter;
import com.github.TACOWASA059.SetWitherTarget.event.EntityEvent;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Mob;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.HashSet;
import java.util.UUID;

public final class SetWitherTarget extends JavaPlugin {

    private HashSet<UUID> entityset=new HashSet();
    public static SetWitherTarget plugin;

    @Override
    public void onEnable() {
        plugin=this;
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        initHashSet();
        repeatingTask();

        getServer().getPluginManager().registerEvents(new EntityEvent(),this);
        getCommand("WitherCommand").setExecutor(new PluginCommand());
        getCommand("WitherCommand").setTabCompleter(new PluginCommandCompleter());
    }
    private void initHashSet(){
        for(World world: Bukkit.getWorlds()){
            for(Entity entity:Bukkit.getWorld(world.getName()).getEntities()){
                if(entity.getType()== EntityType.WITHER)addSet(entity.getUniqueId());
            }
        }
    }
    private void repeatingTask(){
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(SetWitherTarget.plugin, ()->{
                for (UUID uuid:entityset) {
                    Entity entity=Bukkit.getEntity(uuid);
                    if(entity==null) {
                        entityset.remove(uuid);
                        continue;
                    }
                    if (entity.getType() == EntityType.WITHER) {
                        Mob mob = (Mob) entity;
                        if (mob != null) mob.setTarget(Bukkit.getPlayer(SetWitherTarget.plugin.getConfig().getString("MCID")));
                    }
                }
            }, 0L, 5L);
    }
    public void addSet(UUID uuid){
        entityset.add(uuid);
    }
}
