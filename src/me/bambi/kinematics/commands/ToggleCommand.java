package me.bambi.kinematics.commands;

import org.bukkit.command.CommandSender;

public abstract class ToggleCommand extends KinematicsCommand {

    protected abstract void set(boolean bool);

    protected abstract boolean get();

    protected void execute(CommandSender sender, String[] args) throws KinematicsCommandException {
        if(args.length == 0 || args[0].equals("toggle")) {
            this.set(!this.get());
        } else {
            this.set(this.parsBoolean(args[0].toLowerCase()));
        }
    }


}
