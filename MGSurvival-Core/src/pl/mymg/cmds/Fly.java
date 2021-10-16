package pl.mymg.cmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.mymg.main.Main;

import java.util.ArrayList;

public class Fly implements CommandExecutor {

    private ArrayList <Player> list_flying = new ArrayList<>();

    Main plugin;

    public Fly(Main m) {
      plugin = m;
      m.getCommand("fly").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player){
            Player p = (Player) commandSender;
            if(p.hasPermission("mymg.latanie")){
                if (list_flying.contains(p)){
                    list_flying.remove(p);
                    p.setAllowFlight(false);
                    p.sendMessage("§cTryb §6latania §czostal pomyslnie §c§nwylaczony§c!");
                }else if(!list_flying.contains(p)){
                    list_flying.add(p);
                    p.setAllowFlight(true);
                    p.sendMessage("§cTryb §6latania §czostal pomyslnie §awlaczony§c!");
                }
            } else {
                p.sendMessage("§cNie posiadamy takiej komendy.");
            }
        }
        return false;
    }
}
