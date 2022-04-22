/*
 * Copyright (c) 2020-2022  lokka30.
 * This file is/was present in BedDefender's source code.
 * Learn more about BedDefender and its licensing at:
 * <https://github.com/lokka30/BedDefender>
 */
package me.lokka30.beddefender.misc;

public class Utils {

    private Utils() {
        throw new IllegalStateException("Instantiation of utility-type class");
    }

    /*
    Translate all color codes in a given message.
    Tries to use the Bungee Chat API if possible, so that
    hex color codes are possible in 1.16+ servers.
    Falls back on Bukkit's own chat color method.
     */
    public static String colorize(final String msg) {
        if(msg == null || msg.isEmpty()) {
            return msg;
        }

        try {
            return net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', msg);
        } catch(NoSuchMethodError ignored) {
            return org.bukkit.ChatColor.translateAlternateColorCodes('&', msg);
        }
    }

}
