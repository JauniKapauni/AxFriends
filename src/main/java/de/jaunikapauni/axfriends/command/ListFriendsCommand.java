package de.jaunikapauni.axfriends.command;

import de.jaunikapauni.axfriends.AxFriends;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class ListFriendsCommand implements CommandExecutor {
    AxFriends reference;
    public ListFriendsCommand(AxFriends reference){
        this.reference = reference;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage("Only players can run this command!");
            return false;
        }
        Player sourcePlayer = (Player) sender;
        if(!sourcePlayer.hasPermission("axfriends.listfriends")){
            sourcePlayer.sendMessage("You don't have the permission! [axfriends.listfriends]");
            return true;
        }
        sourcePlayer.sendMessage("Your friends:");
        for(String uuid : reference.getPlayerManager().listFriends(sourcePlayer)){
            sourcePlayer.sendMessage("- " + Bukkit.getOfflinePlayer(UUID.fromString(uuid)).getName());
        }
        return true;
    }
}
