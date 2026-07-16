package de.jaunikapauni.axfriends.command;

import de.jaunikapauni.axfriends.AxFriends;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class AddFriendCommand implements CommandExecutor {
    AxFriends reference;
    public AddFriendCommand(AxFriends reference){
        this.reference = reference;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage("Only players can run this command!");
            return false;
        }
        Player sourcePlayer = (Player) sender;
        if(!sourcePlayer.hasPermission("axfriends.addfriend")){
            sourcePlayer.sendMessage("You don't have the permission! [axfriends.addfriend]");
            return true;
        }
        if(args.length != 1){
            return false;
        }
        Player targetPlayer = Bukkit.getPlayer(args[0]);
        if(targetPlayer == null){
            sourcePlayer.sendMessage("Player is not online");
            return true;
        }
        boolean state = reference.getPlayerManager().addFriend(sourcePlayer, targetPlayer);
        if(state){
            sourcePlayer.sendMessage("You have sent " + targetPlayer.getName() + " a request to be your friend!");
            targetPlayer.sendMessage("You got a friend request from " + sourcePlayer.getName());
            return true;
        }
        sourcePlayer.sendMessage("You are already friends with " + targetPlayer.getName());
        return true;
    }
}
