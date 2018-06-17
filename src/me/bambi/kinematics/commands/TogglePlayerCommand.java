package me.bambi.kinematics.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class TogglePlayerCommand extends KinematicsCommand {

    public TogglePlayerCommand(String name) {
        super(name);
    }

    protected abstract void set(boolean bool, Player player);

    protected abstract boolean get(Player player);

    @Override
    protected void execute(CommandSender sender, String[] args) throws KinematicsCommandException {
        if(!(sender instanceof Player)) {
            throw new KinematicsCommandException("Player command Only");
        } else {
            Player p = (Player) sender;
            if(args.length == 0 || args[0].equals("toggle")) {
                this.set(!this.get(p), p);
            } else {
                this.set(this.parsBoolean(args[0].toLowerCase()), p);
            }
        }
    }

}
