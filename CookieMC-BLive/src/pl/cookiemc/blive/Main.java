package pl.cookiemc.blive;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class Main extends Plugin implements Listener {
    public static Configuration config;

    public static ConfigurationProvider cProvider;

    public static File cFile;

    public static String path;

    public HashMap<String, Long> cooldowns = new HashMap<String, Long>();

    public void onEnable() {
        File cFolder = new File(getDataFolder(), "");
        if (!cFolder.exists())
            cFolder.mkdir();
        cFile = new File(getDataFolder() + "/config.yml");
        if (!cFile.exists())
            try {
                String file = "Wiadomosc: '&e« &6&lLIVE &e» Gracz &6%player% &eprowadzi transmisje z &6CookieMC&e! Zacznij ogladac: &6%message%'";
                FileWriter fw = new FileWriter(cFile);
                BufferedWriter out = new BufferedWriter(fw);
                out.write(file);
                out.close();
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        cProvider = ConfigurationProvider.getProvider(YamlConfiguration.class);
        try {
            config = cProvider.load(cFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        path = config.getString("Wiadomosc");
        getProxy().getPluginManager().registerCommand(this, new Command("live") {
            public void execute(CommandSender sender, String[] args) {

                if (sender instanceof ProxiedPlayer) {
                    ProxiedPlayer p = (ProxiedPlayer)sender;
                    if (p.hasPermission("cookiemc.live")) {
                        if (args.length >= 1) {
                            int cooldownTime = 480; //Sekundy
                            if (cooldowns.containsKey(sender.getName())) {
                                long secondsLeft = ((cooldowns.get(sender.getName())/1000) +cooldownTime) - (System.currentTimeMillis()/1000);
                                if(secondsLeft>0) {
                                    p.sendMessage((BaseComponent)new TextComponent(ChatColor.GRAY + "Ogloszenie o transmisji mozesz wyslac za "+ ChatColor.YELLOW + secondsLeft + ChatColor.GRAY + " sekund!"));
                                    return;
                                }
                            }
                            String msg = "";
                            for (int i = 0; i < args.length; i++)
                                msg = String.valueOf(msg) + args[i] + " ";
                            String message = Main.path;
                            message = message.replace("%player%", p.getName());
                            message = message.replace("%message%", msg);
                            BaseComponent[] cp = TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', message));
                            ProxyServer.getInstance().broadcast(cp);
                            cooldowns.put(sender.getName(), System.currentTimeMillis());
                        } else if (args.length < 1) {
                            p.sendMessage((BaseComponent)new TextComponent(ChatColor.RED + "Musisz podac link do transmisji!"));
                        }
                    } else {
                        p.sendMessage((BaseComponent)new TextComponent(ChatColor.RED + "Musisz posiadac range" + ChatColor.YELLOW + " Medialna" + ChatColor.RED + " ,aby zareklamowac transmisje!"));
                    }
                }
            }
        });
    }
}
