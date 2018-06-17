package me.bambi.kinematics.commands;

import org.bukkit.ChatColor;

public class KinematicsCommandException extends Exception {
    private static final String prefix = ChatColor.DARK_RED + "Kinematics syntax error: " + ChatColor.GRAY;

    public KinematicsCommandException(String msg) {
        super(prefix + msg);
    }
}
