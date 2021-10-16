package pl.mymg.cmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.mymg.main.Main;

public class Emotes implements CommandExecutor {

    Main plugin;

    public Emotes(Main m) {
        plugin = m;
        m.getCommand("emotki").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            p.sendMessage("");
            p.sendMessage("§eZakup range §aVIP §ei uzywaj niestandardowych emotek!");
            p.sendMessage("");
            p.sendMessage("§e:lenny: §7- §6( ͡° ͜ʖ ͡°)");
            p.sendMessage("§e:calus: §7 §6˶⚈Ɛ⚈˵");
            p.sendMessage("");
            p.sendMessage("§eRange oraz §6cos §ezakupisz na §6bla bla§e!");
            p.sendMessage("");
        }
        return false;
    }
}
