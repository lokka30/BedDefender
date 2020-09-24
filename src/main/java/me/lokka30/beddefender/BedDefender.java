package me.lokka30.beddefender;

import org.bstats.bukkit.Metrics;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedLeaveEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class BedDefender extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        new Metrics(this, 8941);

        saveDefaultConfig();
        if(!(new File(getDataFolder(), "license.txt").exists())) {
            saveResource("license.txt", false);
        }
    }

    @EventHandler
    public void onBedEnter(final PlayerBedEnterEvent event) {
        if(getConfig().getBoolean("enabled")) {
            //Both of the below do the same thing, just leaving both of them there anyways.
            event.setCancelled(true);
            event.setUseBed(Event.Result.DENY);
        }
    }

    @EventHandler
    public void onBedLeave(final PlayerBedLeaveEvent event) {
        if(getConfig().getBoolean("enabled")) {
            event.setSpawnLocation(false);
        }
    }
}
