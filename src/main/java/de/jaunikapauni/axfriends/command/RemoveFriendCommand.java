package de.jaunikapauni.axfriends.command;

import de.jaunikapauni.axfriends.AxFriends;
import de.jaunikapauni.axfriends.manager.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class RemoveFriendCommand implements CommandExecutor {
    AxFriends reference;
    public RemoveFriendCommand(AxFriends reference){
        this.reference = reference;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage("Only players can run this command!");
            return true;
        }
        Player sourcePlayer = (Player) sender;
        if(!sourcePlayer.hasPermission("axfriends.removefriend")){
            sourcePlayer.sendMessage("You don't have the permission! [axfriends.removefriend]");
            return true;
        }
        Player targetPlayer = Bukkit.getPlayer(args[0]);
        boolean state = reference.getPlayerManager().removeFriend(sourcePlayer, targetPlayer);
        if(state){
            sourcePlayer.sendMessage("You are not longer friends with " + targetPlayer.getName());
            targetPlayer.sendMessage(sourcePlayer.getName() + " removed you as a friend!");
            return true;
        }
        sourcePlayer.sendMessage("You are not friends with " + targetPlayer.getName());
        return true;
    }
}
