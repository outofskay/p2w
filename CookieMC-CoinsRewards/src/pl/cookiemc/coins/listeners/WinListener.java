package pl.cookiemc.coins.listeners;

import com.andrei1058.bedwars.api.arena.GameState;
import com.andrei1058.bedwars.api.events.gameplay.GameStateChangeEvent;
import pl.cookiemc.coins.ConfigPath;
import pl.cookiemc.coins.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class WinListener implements Listener {
    @EventHandler
    public void onWin(GameStateChangeEvent e) {
        if (e.getNewState() == GameState.restarting)
            Bukkit.getScheduler().runTaskLater((Plugin)Main.getPlugin(), () -> {
                for (Player p : e.getArena().getPlayers()) {
                    for (String s : Main.getCfg().getYml().getStringList(ConfigPath.GAME_WIN_WINNER_CMDS_AS_PLAYER))
                        Bukkit.getServer().dispatchCommand((CommandSender)p, "/" + s.replace("{player}", p.getName().replace("{arena}", e.getArena().getWorldName())).replace("{arenaDisplay}", e.getArena().getDisplayName()).replace("{group}", e.getArena().getGroup()));
                    for (String s : Main.getCfg().getYml().getStringList(ConfigPath.GAME_WIN_WINNER_CMDS_AS_CONSOLE))
                        Bukkit.getServer().dispatchCommand((CommandSender)Bukkit.getConsoleSender(), s.replace("{player}", p.getName().replace("{arena}", e.getArena().getWorldName())).replace("{arenaDisplay}", e.getArena().getDisplayName()).replace("{group}", e.getArena().getGroup()));
                }
            }10L);
    }
}
