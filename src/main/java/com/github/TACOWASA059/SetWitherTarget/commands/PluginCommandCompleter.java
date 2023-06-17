package com.github.TACOWASA059.SetWitherTarget.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class PluginCommandCompleter implements TabCompleter {
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        List<String> list=new ArrayList<>();
        if(args.length==1){
            list.add("setTarget");
            list.add("getTarget");
            list.add("usage");
        }
        else if(args.length==2&&args[0].equalsIgnoreCase("setTarget")){
            for(Player player: Bukkit.getOnlinePlayers()){
                list.add(player.getName());
            }
        }

        return list;
    }
}
