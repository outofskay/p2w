package pl.mymg.cmds;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.mymg.api.RanksAPI;
import pl.mymg.main.Main;

public class Rank implements CommandExecutor {

    Main plugin;
    RanksAPI ra;

    public Rank(Main m) {
        plugin = m;
        m.getCommand("grupa").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command arg1, String args2, String[] args) {
        if (args.length == 3) {
            if (args[0].equalsIgnoreCase("nadaj")) {
                String rank = args[1];
                Player gracz = Bukkit.getPlayerExact(args[2]);

                ra.setRank(gracz, rank);

                sender.sendMessage("Gracz" + gracz.getDisplayName() + "zostal dodany do grupy" + rank);
            } else if (args[0].equalsIgnoreCase("setdefault")) {
                if (args[1].equalsIgnoreCase("grupa")) {
                    String rank = args[2];

                    ra.setDefaultRank(rank);

                    sender.sendMessage("Ustawiles grupe" + rank);
                }
            }
        } else if (args.length == 4) {
            if (args[0].equalsIgnoreCase("grupa")) {
                if (args[1].equalsIgnoreCase("dodaj")) {
                    String rank = args[2];
                    String permission = args[3];

                    ra.removeRankPermission(rank, permission);

                    sender.sendMessage("Usunales uprawnienie" + permission + "z grupy" + rank);
                } else if (!args[1].equalsIgnoreCase("prefix")) {
                    if (args[1].equalsIgnoreCase("stworz")) {
                        String rank = args[2];
                        String prefix = args[3].replace("&", "§");

                        ra.createRank(rank, prefix);

                        sender.sendMessage("Stworzyles range" + rank + "z prefixem" + prefix);
                    }
                } else {
                    String rank = args[2];
                    String prefix = args[3].replace("&", "§");

                    ra.setRankPrefix(rank, prefix);

                    sender.sendMessage("Ustawiles prefix" + prefix + "dla grupy" + rank);
                }
            }
        } else {
            sender.sendMessage("/grupa nadaj [ranga] [gracz]");
            sender.sendMessage("/grupa setdefault rank [ranga]");
            sender.sendMessage("/grupa group dodaj [ranga] [uprawnienie]");
            sender.sendMessage("/grupa group usun ]ranga] [uprawnienie]");
            sender.sendMessage("/grupa group prefix [ranga] [prefix]");
            sender.sendMessage("/grupa group stworz [ranga] [prefix]");
        }
        return false;
    }
}
