package pl.mymg.main;

import org.bukkit.plugin.java.JavaPlugin;
import pl.mymg.api.RanksAPI;
import pl.mymg.cmds.*;
import pl.mymg.data.RanksData;
import pl.mymg.events.onAsyncPlayer;
import pl.mymg.events.onJoin;

public class Main extends JavaPlugin {

    public static Main main;
    RanksData rd;

    public Main() {
        rd = RanksData.getInstance();
    }

    public void onEnable() {
        main = this;

        new Chat(this);
        new Gamemode(this);
        new Heal(this);
        new Feed(this);
        new Fly(this);
        new Pomoc(this);
        new Emotes(this);
        new Report(this);
        new Rank(this);
        new RanksAPI(this);

        getServer().getPluginManager().registerEvents(new onAsyncPlayer(), this);
        getServer().getPluginManager().registerEvents(new onJoin(), this);
    }

    public static Main getMain() {
        return main;
    }
}
