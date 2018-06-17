package me.bambi.kinematics.commands;

import org.bukkit.command.CommandSender;

public abstract class VectorCommand extends KinematicsCommand {

    protected abstract void setX(double d);
    protected abstract void setY(double d);
    protected abstract void setZ(double d);

    protected abstract String getMessage();

    @Override
    protected void execute(CommandSender sender, String[] args) throws KinematicsCommandException {
        if(args.length > 2) {
            double x = this.parsDouble(args[0]);
            double y = this.parsDouble(args[1]);
            double z = this.parsDouble(args[2]);
            this.setX(x);
            this.setY(y);
            this.setZ(z);
        } else if (args.length == 2) {
            double d = this.parsDouble(args[1]);
            if(args[0].contains("x")) {
                this.setX(d);
            }
            if(args[0].contains("y")) {
                this.setY(d);
            }
            if(args[0].contains("z")) {
                this.setZ(d);
            }
        } else if (args.length == 1 && !args[0].equals("show")) {
            double d = this.parsDouble(args[0]);
            this.setX(d);
            this.setY(d);
            this.setZ(d);
        }

        sender.sendMessage(PREFIX + this.getMessage());
    }
}
