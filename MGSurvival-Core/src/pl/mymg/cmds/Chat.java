package pl.mymg.cmds;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.mymg.main.Main;

public class Chat implements CommandExecutor {

    Main plugin;

    public Chat(Main m) {
        plugin = m;
        m.getCommand("chat").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
        if (sender.isOp()) {
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("clear")) {
                    for (Player ps : Bukkit.getOnlinePlayers()) {
                        for (int i = 1; i < 101; i++) {
                            ps.sendMessage(" ");
                        }
                        ps.sendMessage("§cChat zostal §bwyczyszczony §cprzez §6" + sender.getName());
                    }
                }
            } else {
                sender.sendMessage("§cPoprawne uzycie: §6/chat (clear)");
            }
        } else {
            sender.sendMessage("§cNie posiadamy takiej komendy.");
        }
        return false;
    }
}
