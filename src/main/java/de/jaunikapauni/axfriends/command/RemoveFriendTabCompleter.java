package de.jaunikapauni.axfriends.command;

import de.jaunikapauni.axfriends.AxFriends;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RemoveFriendTabCompleter implements TabCompleter {
    AxFriends reference;

    public RemoveFriendTabCompleter(AxFriends reference) {
        this.reference = reference;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if (!(sender instanceof Player)) {
            return List.of();
        }
        if (args.length != 1) {
            return List.of();
        }
        List<String> playerNames = new ArrayList<>();
        Player p = (Player) sender;

        for (String uuid : reference.getPlayerManager().listFriends(p)) {
            OfflinePlayer player = Bukkit.getOfflinePlayer(UUID.fromString(uuid));
            if (player.getName() != null) {
                playerNames.add(player.getName());
            }
        }
        return playerNames;
    }
}
