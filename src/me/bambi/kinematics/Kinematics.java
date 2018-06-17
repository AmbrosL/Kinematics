package me.bambi.kinematics;

import me.bambi.kinematics.commands.KinematicsCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Collection;

public class Kinematics extends JavaPlugin {

    @Override
    public void onEnable() {
        loadCommands();

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
}
