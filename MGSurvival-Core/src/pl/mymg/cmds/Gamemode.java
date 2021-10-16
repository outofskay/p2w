package pl.mymg.cmds;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.mymg.main.Main;

public class Gamemode implements CommandExecutor {

    Main plugin;

    public Gamemode(Main m) {
        plugin = m;
        m.getCommand("gm").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
        if(sender.isOp()) {
            if(args.length == 1) {
                if(sender instanceof Player) {
                    Player p = (Player) sender;

                    if (args[0].equalsIgnoreCase("0") || args[0].equalsIgnoreCase("s")) {
                        p.setGameMode(GameMode.SURVIVAL);
                        p.sendMessage("§cTwoj tryb gry zostal zmieniony na §aSurvival§c!");
                    } else if (args[0].equalsIgnoreCase("1") || args[0].equalsIgnoreCase("c")) {
                        p.setGameMode(GameMode.CREATIVE);
                        p.sendMessage("§cTwoj tryb gry zostal zmieniony na §aKreatywny§c!");
                    } else if (args[0].equalsIgnoreCase("2") || args[0].equalsIgnoreCase("a")) {
                        p.setGameMode(GameMode.ADVENTURE);
                        p.sendMessage("§cTwoj tryb gry zostal zmieniony na §aPrzygodowy§c!");
                    } else if (args[0].equalsIgnoreCase("3") || args[0].equalsIgnoreCase("spec")) {
                        p.setGameMode(GameMode.SPECTATOR);
                        p.sendMessage("§cTwoj tryb gry zostal zmieniony na §aObserwatora§c!");
                    }
                } else {
                    sender.sendMessage("Komende mozesz uzyc tylko z poziomu gracza!");
                }
            } else {
                sender.sendMessage("§cPoprawne uzycie: §6/gm (0/1/2/3)");
            }
        } else {
            sender.sendMessage("§cNie posiadamy takiej komendy.");
        }
        return false;
    }
}
