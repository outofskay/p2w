package pl.mymg.cmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.mymg.main.Main;

public class Pomoc implements CommandExecutor {

    Main plugin;

    public Pomoc(Main m) {
        plugin = m;
        m.getCommand("pomoc").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("pomoc")) {
            if (sender instanceof Player) {
                sender.sendMessage(" ");
                sender.sendMessage("§8 §6/sklep §8- §cSklep globalny");
                sender.sendMessage("§8 §6/rynek §8- §cRynek u Zdzisia");
                sender.sendMessage("§8 §6/zglos §8- §cZglos blad administracji badz skontaktuj sie z nia!");
                sender.sendMessage(" ");
                sender.sendMessage("§cDISCORD §6www.mymg.pl/discord");
                sender.sendMessage("§cSKLEP §6www.mymg.pl");
                sender.sendMessage("§cFACEBOOK §6fb.com/mymgpl");
                sender.sendMessage(" ");
            }
        }
        return true;
    }
}
