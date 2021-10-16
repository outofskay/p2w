package pl.mymg.cmds;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.mymg.main.Main;

public class Heal implements CommandExecutor {

    Main plugin;

    public Heal(Main m) {
        plugin = m;
        m.getCommand("ulecz").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
        if(sender.hasPermission("mymg.lecz")) {
        if (args.length == 0) {
            if (sender instanceof Player) {
                Player p = (Player) sender;

                p.setHealth(20);
                p.setFoodLevel(20);
                p.setFireTicks(0);
                p.sendMessage("§cZostales pomyslnie uleczony!");
            }
        } else if (args.length == 1) {
            Player gracz = Bukkit.getPlayerExact(args[0]);

            if (gracz != null) {
                gracz.setHealth(20);
                gracz.setFoodLevel(20);
                gracz.setFireTicks(0);
                gracz.sendMessage("§cZostales uleczony przez §6" + sender.getName());

                sender.sendMessage("§cPomyslnie uleczyles gracza §6" + gracz.getDisplayName());
            } else {
                sender.sendMessage("§cNie odnaleziono gracza!");
            }
        } else {
            sender.sendMessage("§cPoprawne uzycie: §6/ulecz (gracz)");
        }
    } else {
            sender.sendMessage("§cNie posiadamy takiej komendy.");
        }
    return false;
    }
}
