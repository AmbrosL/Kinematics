package me.bambi.kinematics;

import me.bambi.kinematics.commands.KinematicsCommand;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Collection;

public class Kinematics extends JavaPlugin {

    public static Settings settings = new Settings();

    @Override
    public void onEnable() {
        loadCommands();
        loadConfig();

    }

    @Override
    public void onDisable() {

    }

    private void loadCommands() {
        Collection<KinematicsCommand> commands = new ArrayList<>();

        for(KinematicsCommand command : commands) {
            getCommand(command.getName()).setExecutor(command);
        }
    }

    private void loadConfig() {
        this.saveDefaultConfig();
        FileConfiguration config = this.getConfig();

        settings.enable_protection = config.getBoolean("protection.enable");
        for(String s : config.getStringList("protection.not_protected")) {
            try {
                settings.not_protected_materials.add(Material.valueOf(s.toUpperCase()));
            } catch (Exception e) {
                getLogger().info(String.format("couldn't load %s as a Material", s));
            }
        }


    }
}
