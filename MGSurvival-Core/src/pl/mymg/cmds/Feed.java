package pl.mymg.cmds;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.mymg.main.Main;

public class Feed implements CommandExecutor {

    Main plugin;

    public Feed(Main m) {
        plugin = m;
        m.getCommand("glod").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
        if(sender.hasPermission("mymg.lecz")) {
            if (args.length == 0) {
                if (sender instanceof Player) {
                    Player p = (Player) sender;

                    p.setFoodLevel(20);
                    p.sendMessage("§cTwoj glod zostal pomyslnie zaspokojony!");
                }
            } else if (args.length == 1) {
                Player gracz = Bukkit.getPlayerExact(args[0]);

                if (gracz != null) {
                    gracz.setFoodLevel(20);
                    gracz.sendMessage("§cTwoj glod zostal zaspokojony przez §6" + sender.getName());

                    sender.sendMessage("§cPomyslnie zaspokoiles glod gracza §6" + gracz.getDisplayName());
                } else {
                    sender.sendMessage("§cNie odnaleziono gracza!");
                }
            } else {
                sender.sendMessage("§cPoprawne uzycie: §6/glod (gracz)");
            }
        } else {
            sender.sendMessage("§cNie posiadamy takiej komendy.");
        }
        return false;
    }
}
