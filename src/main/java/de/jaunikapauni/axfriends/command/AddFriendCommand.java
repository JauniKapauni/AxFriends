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
        Player targetPlayer = Bukkit.getPlayer(args[0]);
        boolean state = reference.getPlayerManager().addFriend(sourcePlayer, targetPlayer);
        if(state){
            sourcePlayer.sendMessage("You are now friends with " + targetPlayer.getName());
            targetPlayer.sendMessage("You are now friends with " + sourcePlayer.getName());
            return true;
        }
        sourcePlayer.sendMessage("You are already friends with " + targetPlayer.getName());
        return true;
    }
}
