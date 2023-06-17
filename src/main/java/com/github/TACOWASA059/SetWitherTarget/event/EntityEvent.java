package com.github.TACOWASA059.SetWitherTarget.event;

import com.github.TACOWASA059.SetWitherTarget.SetWitherTarget;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntitySpawnEvent;


public class EntityEvent implements Listener {
    @EventHandler
    public void onEntitySpawnEvent(EntitySpawnEvent e){
        Entity entity=e.getEntity();
        setTarget(entity);
    }
    @EventHandler
    public void onEntityDamageEvent(EntityDamageEvent e){
        Entity entity=e.getEntity();
        setTarget(entity);
    }
    private void setTarget(Entity entity){
        if(entity.getType()== EntityType.WITHER){
            Mob mob=(Mob) entity;
            LivingEntity livingEntity= Bukkit.getPlayer(SetWitherTarget.plugin.getConfig().getString("MCID"));
            if(livingEntity!=null)mob.setTarget(livingEntity);
            Bukkit.getScheduler().runTaskLater(SetWitherTarget.plugin,()->{
                SetWitherTarget.plugin.addSet(entity.getUniqueId());
            },2L);
        }
    }
}
