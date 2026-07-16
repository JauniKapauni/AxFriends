package de.jaunikapauni.axfriends.command;

import de.jaunikapauni.axfriends.AxFriends;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class AcceptFriendRequestCommand implements CommandExecutor {
    AxFriends reference;
    public AcceptFriendRequestCommand(AxFriends reference){
        this.reference = reference;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage("Only players can run this command!");
            return true;
        }
        Player sourcePlayer = (Player) sender;
        if(!sourcePlayer.hasPermission("axfriends.acceptfriendrequest")){
            sourcePlayer.sendMessage("You don't have the permission! [axfriends.acceptfriendrequest]");
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
        boolean state = reference.getPlayerManager().acceptFriendRequest(sourcePlayer, targetPlayer);
        if(state){
            targetPlayer.sendMessage(sourcePlayer.getName() + " has accept your friend request!");
            sourcePlayer.sendMessage("You accepted the friend request from " + targetPlayer.getName());
            return true;
        }
        sourcePlayer.sendMessage("There are no pending friend requests.");
        return true;
    }
}
