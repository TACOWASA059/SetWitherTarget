package com.github.TACOWASA059.SetWitherTarget.scoreboard;

import com.github.TACOWASA059.SetWitherTarget.SetWitherTarget;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;

import java.math.BigInteger;
import java.util.UUID;

public class saveWitherUUID {//ランク
    private static org.bukkit.scoreboard.Scoreboard scoreboard;
    private static Objective objective;
    //コンストラクタ宣言
    public saveWitherUUID(){
        init();
        repeatingTask();
    }
    private  void init(){
        this.scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
        this.objective = scoreboard.getObjective("saveWitherUUID");
        if (objective == null) objective = scoreboard.registerNewObjective("saveWitherUUID", "dummy","ウィザーuuid");
        for(World world:Bukkit.getWorlds()){
            for(Entity entity:Bukkit.getWorld(world.getName()).getEntities()){
                if(entity.getType()==EntityType.WITHER)setScore(entity);
            }
        }
    }
    public void setScore(Entity entity){
        Score score=this.objective.getScore(entity.getUniqueId().toString());
        score.setScore(1);
    }

    private void repeatingTask(){
            Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(SetWitherTarget.plugin, new Runnable() {
                public void run() {
                    try {
                        repeatingSetTarget();
                    }catch (IllegalStateException e){
                        init();
                        repeatingSetTarget();
                    }
            }}, 0L, 5L);
    }
    private void repeatingSetTarget(){
        for (String entry : scoreboard.getEntries()) {
            Score score = objective.getScore(entry);
            if (score == null || !score.isScoreSet()) continue;
            try{
                String uuid_string = score.getEntry();
                Entity entity = Bukkit.getEntity(UUID.fromString(uuid_string));
                if (entity == null){
                    scoreboard.resetScores(score.getEntry());
                }
                else if (entity.getType() == EntityType.WITHER) {
                    Mob mob = (Mob) entity;
                    if (mob != null)
                        mob.setTarget(Bukkit.getPlayer(SetWitherTarget.plugin.getConfig().getString("MCID")));
                }
                else{
                    scoreboard.resetScores(score.getEntry());
                }
            }catch (IllegalArgumentException e){
                scoreboard.resetScores(score.getEntry());
            }

        }
    }


}

