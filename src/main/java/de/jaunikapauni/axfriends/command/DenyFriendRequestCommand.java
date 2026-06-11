package de.jaunikapauni.axfriends.command;

import de.jaunikapauni.axfriends.AxFriends;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;

public class DenyFriendRequestCommand implements CommandExecutor {
    AxFriends reference;
    public DenyFriendRequestCommand(AxFriends reference){
        this.reference = reference;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage("Only players can run this command!");
            return true;
        }
        Player sourcePlayer = (Player) sender;
        if(!sourcePlayer.hasPermission("axfriends.denyfriendrequest")){
            sourcePlayer.sendMessage("You don't have the permission! [axfriends.denyfriendrequest]");
            return true;
        }
        Player targetPlayer = Bukkit.getPlayer(args[0]);
        boolean state = false;
        try {
            state = reference.getPlayerManager().denyFriendRequest(sourcePlayer, targetPlayer);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(state){
            targetPlayer.sendMessage(sourcePlayer.getName() + " has denied your friend request!");
            sourcePlayer.sendMessage("You denied the friend request from " + targetPlayer.getName());
            return true;
        }
        sourcePlayer.sendMessage("There are no pending friend requests.");
        return true;
    }
}
