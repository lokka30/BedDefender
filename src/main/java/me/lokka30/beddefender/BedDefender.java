/*
 * Copyright (c) 2020-2022  lokka30.
 * This file is/was present in BedDefender's source code.
 * Learn more about BedDefender and its licensing at:
 * <https://github.com/lokka30/BedDefender>
 */
package me.lokka30.beddefender;

import me.lokka30.beddefender.listener.BedListeners;
import org.bstats.bukkit.Metrics;
import org.bstats.charts.SimplePie;
import org.bukkit.plugin.java.JavaPlugin;

public class BedDefender extends JavaPlugin {

    @Override
    public void onEnable() {
        loadFiles();
        registerListeners();
        loadMetrics();
    }

    private void loadFiles() {
        getLogger().info("Loading files...");
        saveDefaultConfig();
    }

    private void registerListeners() {
        getLogger().info("Registering listeners...");
        getServer().getPluginManager().registerEvents(new BedListeners(this), this);
    }

    private void loadMetrics() {
        final Metrics metrics = new Metrics(this, 8941);
        metrics.addCustomChart(new SimplePie("uses_bed_usage_prevention", () ->
            Boolean.toString(getConfig().getBoolean("prevent-bed-usage", true))
        ));
        metrics.addCustomChart(new SimplePie("uses_respawn_set_prevention", () ->
            Boolean.toString(getConfig().getBoolean("prevent-respawn-set", false))
        ));
        metrics.addCustomChart(new SimplePie("uses_send_message", () ->
            Boolean.toString(getConfig().getBoolean("message.send", true))
        ));
    }
}
