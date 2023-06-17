package com.github.TACOWASA059.SetWitherTarget.commands;

import com.github.TACOWASA059.SetWitherTarget.SetWitherTarget;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


public class PluginCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player ){
            Player player=(Player) sender;
            if(!player.isOp()){
                player.sendMessage(ChatColor.RED+"コマンドを実行する権限がありません。");
                return true;
            }

            if(args.length==1&&args[0].equalsIgnoreCase("getTarget")){
                player.sendMessage(ChatColor.GREEN+"ターゲット : "+ SetWitherTarget.plugin.getConfig().getString("MCID"));
            }
            else if(args.length==2 && args[0].equalsIgnoreCase("setTarget")){
                String player_name=args[1];
                Player player1= Bukkit.getPlayer(player_name);
                if(player1==null){
                    player.sendMessage(ChatColor.RED+"正しいMCIDを入力して下さい。");
                    return true;
                }
                SetWitherTarget.plugin.getConfig().set("MCID",player_name);
                SetWitherTarget.plugin.saveConfig();
                player.sendMessage(ChatColor.GREEN+"ターゲットは"+ChatColor.AQUA+ SetWitherTarget.plugin.getConfig().getString("MCID")+ChatColor.GREEN+"に変更されました。");
            }
            else if (args.length==1&&args[0].equalsIgnoreCase("usage")){
                player.sendMessage(ChatColor.LIGHT_PURPLE+"-------------------");
                player.sendMessage(ChatColor.GREEN+"/WitherCommand getTarget : ターゲットのMCIDを取得");
                player.sendMessage(ChatColor.GREEN+"/WitherCommand setTarget MCID : ターゲットのMCIDを設定");
                player.sendMessage(ChatColor.GREEN+"/WitherCommand usage : コマンド使用法の表示");
                player.sendMessage(ChatColor.LIGHT_PURPLE+"-------------------");
            }
            else{
                player.sendMessage(ChatColor.RED+"コマンドが間違っています.");
            }
        }

        return true;
    }


}
