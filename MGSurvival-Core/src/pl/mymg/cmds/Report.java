package pl.mymg.cmds;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.mymg.main.Main;

import java.util.HashMap;

public class Report implements CommandExecutor {

    Main plugin;

    HashMap<Player, Long> spam = new HashMap<Player, Long>();

    public Report (Main m) {
        plugin = m;
        m.getCommand("zglos").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
        if (sender instanceof Player) {
            if (args.length >= 1) {

                Player p = (Player) sender;

                if (spam.containsKey(p)) {
                    if (spam.get(p) > System.currentTimeMillis()) {
                        p.sendMessage("§cPrzed ponownym zgloszeniem musisz poczekac §e30 §csekund!");
                    } else {
                        StringBuilder sb = new StringBuilder();

                        for (int i = 0; i < args.length; ++i) {
                            sb.append(args[i]).append(" ");
                        }

                        String msg = sb.toString();

                        for (Player ps : Bukkit.getOnlinePlayers()) {
                            if (ps.isOp()) {
                                ps.sendMessage("§c§lZGLOSZENIE §8» §e" + p.getDisplayName() + " §7: " + msg);
                            }
                        }

                        spam.put(p, System.currentTimeMillis() + 30 * 1000);
                    }
                } else {
                    StringBuilder sb = new StringBuilder();

                    for (int i = 0; i < args.length; ++i) {
                        sb.append(args[i]).append(" ");
                    }

                    String msg = sb.toString();

                    for (Player ps : Bukkit.getOnlinePlayers()) {
                        if (ps.isOp()) {
                            ps.sendMessage("§c§lZGLOSZENIE §8» §e" + p.getDisplayName() + " §7: " + msg);
                        }
                    }

                    spam.put(p, System.currentTimeMillis() + 30 * 1000);
                }
            } else {
                sender.sendMessage("§cMusisz podac tresc zgloszenia!");
            }
        }
        return false;
    }
}
