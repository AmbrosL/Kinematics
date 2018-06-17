package me.bambi.kinematics.commands;

import me.bambi.kinematics.Kinematics;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;

public class CommandProtection extends ToggleCommand {

    public CommandProtection() {
        super("protection");
        this.addAlias("prot");
    }

    @Override
    protected void set(boolean bool) {
        Kinematics.settings.enable_protection = bool;
    }

    @Override
    protected boolean get() {
        return Kinematics.settings.enable_protection;
    }

    @Override
    protected void execute(CommandSender sender, String[] args) throws KinematicsCommandException {
        if(args.length == 1 && args[0].equals("list")) {
          //TODO list protected materials
        } else if (args.length > 1) {
            switch (args[0]) {
                case "add":
                    try {
                        Kinematics.settings.not_protected_materials.add(Material.valueOf(args[1]));
                    } catch (Exception e) {
                        throw new KinematicsCommandException(String.format("unable to pars %s to a material", args[0]));
                    }
                    return;
                case "remove":
                case "delete":
                    try {
                        Kinematics.settings.not_protected_materials.remove(Material.valueOf(args[1]));
                    } catch (Exception e) {
                        throw new KinematicsCommandException(String.format("unable to pars %s to a material", args[0]));
                    }
                    return;
            }
        }
        super.execute(sender, args);
    }
}
