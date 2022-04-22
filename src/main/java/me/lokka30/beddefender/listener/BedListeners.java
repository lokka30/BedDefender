/*
 * Copyright (c) 2020-2022  lokka30.
 * This file is/was present in BedDefender's source code.
 * Learn more about BedDefender and its licensing at:
 * <https://github.com/lokka30/BedDefender>
 */
package me.lokka30.beddefender.listener;

import me.lokka30.beddefender.BedDefender;
import me.lokka30.beddefender.misc.Utils;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedLeaveEvent;

public class BedListeners implements Listener {

    private final BedDefender main;

    public BedListeners(final BedDefender main) {
        this.main = main;
    }

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onBedEnter(final PlayerBedEnterEvent event) {
        if(main.getConfig().getBoolean("prevent-bed-usage", true)) {
            // Both of the below do the same thing, just leaving both of them there for redundancy.
            event.setCancelled(true);
            event.setUseBed(Event.Result.DENY);

            if(main.getConfig().getBoolean("message.send")) {
                event.getPlayer().sendMessage(Utils.colorize(main.getConfig().getString(
                    "message.text",
                    "Sleeping in beds is disabled on this server."
                )));
            }
        }
    }

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onBedLeave(final PlayerBedLeaveEvent event) {
        if(main.getConfig().getBoolean("prevent-respawn-set", false)) {
            event.setSpawnLocation(false);
        }
    }

}
