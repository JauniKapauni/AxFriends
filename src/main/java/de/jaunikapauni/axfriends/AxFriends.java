package de.jaunikapauni.axfriends;

import de.jaunikapauni.axfriends.command.*;
import de.jaunikapauni.axfriends.manager.DatabaseManager;
import de.jaunikapauni.axfriends.manager.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class AxFriends extends JavaPlugin {
    DatabaseManager databaseManager;
    public DatabaseManager getDatabaseManager(){
        return databaseManager;
    }
    PlayerManager playerManager;
    public PlayerManager getPlayerManager(){
        return playerManager;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        try{
            databaseManager = new DatabaseManager(this);
            playerManager = new PlayerManager(this);
            if(databaseManager.initDatabaseTable1() && databaseManager.initDatabaseTable2() == false){
                getLogger().severe("Error creating tables!");
                Bukkit.getServer().shutdown();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        getCommand("addfriend").setExecutor(new AddFriendCommand(this));
        getCommand("listfriends").setExecutor(new ListFriendsCommand(this));
        getCommand("removefriend").setExecutor(new RemoveFriendCommand(this));
        getCommand("acceptfriendrequest").setExecutor(new AcceptFriendRequestCommand(this));
        getCommand("denyfriendrequest").setExecutor(new DenyFriendRequestCommand(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
