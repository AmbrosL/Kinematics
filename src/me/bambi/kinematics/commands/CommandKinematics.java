package me.bambi.kinematics.commands;

import org.bukkit.command.CommandSender;

public class CommandKinematics extends KinematicsCommand {

    public CommandKinematics() {
        super("kinematics");
        this.addAlias("kin");
        this.addAlias("k");

        this.addSubCommand(new CommandProtection());
    }
    @Override
    protected void execute(CommandSender sender, String[] args) throws KinematicsCommandException {

    }
}
