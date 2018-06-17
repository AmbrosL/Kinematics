package me.bambi.kinematics.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.*;

import java.util.*;

public abstract class KinematicsCommand implements CommandExecutor, TabCompleter {

    protected final String name;
    protected HashSet<KinematicsCommand> subcommands = new HashSet<KinematicsCommand>();
    protected HashSet<String> aliases = new HashSet<String>();
    protected static final String PREFIX = ChatColor.AQUA + "Kinematics " + ChatColor.GRAY;

    public KinematicsCommand(String name) {
        this.name = name.toLowerCase();
    }

    protected abstract void execute(CommandSender sender, String[] args) throws KinematicsCommandException;

    protected boolean execute(CommandSender sender, String label, String args[]) throws KinematicsCommandException {

        if(!(label.equals(this.name) || aliases.contains(label))) {
            return false;
        }

        if(args.length > 0) {
            String[] new_args = shiftargs(args);
            for(KinematicsCommand sub : this.subcommands) {
                if(sub.execute(sender, args[0], new_args)) return true;
            }
        }

        execute(sender, args);

        return true;
    }

    protected List<String> TabComplete(CommandSender sender, String label, String[] args) {
        List<String> list = new ArrayList<String>();
        if(!(label.equals(this.name) || aliases.contains(label))) {
            return list;
        }
        if(args.length > 0) {
            String arg = args[0];
            String[] new_args = shiftargs(args);
            for(KinematicsCommand sub : subcommands) {
                list.addAll(sub.TabComplete(sender, arg, new_args));
            }
            if(list.isEmpty() && args.length == 1) {
                for(KinematicsCommand sub : subcommands) {
                    if(sub.name.startsWith(arg)) {
                        list.add(sub.name);
                    } else {
                        for(String ali : sub.aliases) {
                            if(ali.startsWith(arg)) {
                                list.add(arg);
                                break;
                            }
                        }
                    }
                }
            }
        } else {
            for(KinematicsCommand sub : subcommands) {
                list.add(sub.name);
            }
        }
        if(list.isEmpty()) list.addAll(TabComplete());
        return list;
    }

    protected List<String> TabComplete() {
        return Collections.emptyList();
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        args = argstolowercase(args);

        try {
            execute(sender, label, args);
        } catch (KinematicsCommandException e) {
            sender.sendMessage(e.getMessage());
            return false;
        }
        return true;
    }

    public List<String> onTabComplete(CommandSender sender , Command command, String label, String[] args) {
        return TabComplete(sender, label, args);
    }

    public String getName() {
        return name;
    }

    public Collection<String> getAliases() {
        return aliases;
    }

    public void addAlias(String ali) {
        aliases.add(ali);
    }

    public void addAliases(Collection<String> ali) {
        aliases.addAll(ali);
    }

    public void addSubCommand(KinematicsCommand sub) {
        subcommands.add(sub);
    }

    protected int parsInt(String arg) throws KinematicsCommandException {
        try {
            return Integer.parseInt(arg);
        } catch (Exception e) {
            throw new KinematicsCommandException(ChatColor.GRAY + "Unable to pars " + ChatColor.YELLOW + arg + ChatColor.GRAY + " to Integer");
        }
    }

    protected double parsDouble(String arg) throws KinematicsCommandException {
        try {
            return Double.parseDouble(arg);
        } catch (Exception e) {
            throw new KinematicsCommandException(ChatColor.GRAY + "Unable to pars " + ChatColor.YELLOW + arg + ChatColor.GRAY + " to a floating point number");
        }
    }

    protected boolean parsBoolean(String arg) throws  KinematicsCommandException {
        switch (arg.toLowerCase()) {
            case "on":
            case "enable":
            case "en":
            case "1":
            case "true":
                return true;
            case "off":
            case "disable":
            case "dis":
            case "0":
            case "false":
                return false;
            default:
                throw new KinematicsCommandException(String.format("Unable to pars %s to a boolean value", arg));
        }
    }

    private String[] shiftargs(String[] args, int n) {
        String[] new_args = new String[args.length-n];
        for(int i = new_args.length; i > 0; i--) {
            new_args[i-n] = args[i];
        }
        return new_args;
    }

    private String[] shiftargs(String[] args) {
        return this.shiftargs(args,1);
    }

    private String[] argstolowercase(String[] args) {
        String[] new_args = new String[args.length];
        for(int i = new_args.length; i > 0; i--) {
            new_args[i] = args[i].toLowerCase();
        }
        return new_args;
    }

}
