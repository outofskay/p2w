package pl.cookiemc.coins;

import com.andrei1058.bedwars.BedWars;
import com.andrei1058.bedwars.api.configuration.ConfigManager;
import com.andrei1058.bedwars.api.events.gameplay.GameEndEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import pl.cookiemc.coins.listeners.WinListener;

public class Main  extends JavaPlugin implements Listener {
    private static Main plugin;

    private static ConfigManager cfg;

    private static BedWars api;

    @Override
    public void onEnable() {
        plugin = this;
        if (Bukkit.getPluginManager().getPlugin("BedWars1058") == null) {
            getLogger().severe("Nie uruchomisz tego pluginu bez BedWars1058!");
            setEnabled(false);
            return;
        }
        api = (BedWars)Bukkit.getServicesManager().getRegistration(BedWars.class).getProvider();
        cfg = new ConfigManager(Plugin)this, "config", "plugins/BedWars1058/Addons/Cmds");
        ();
        if (getCfg().getBoolean(ConfigPath.GAME_WIN_ENABLE))
            Bukkit.getPluginManager().registerEvents((Listener)new WinListener(), (Plugin)getPlugin());

        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player player = e.getEntity();
        Player killer = e.getEntity().getKiller();
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "alonsolevels addexp " + killer.getDisplayName() + " 10");
    }

    public void onWin(GameEndEvent e) {
        Player w = (Player) e.getTeamWinner();
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "say test");
        w.sendMessage("test");
    }
}