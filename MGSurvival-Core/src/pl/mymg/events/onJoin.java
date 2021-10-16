package pl.mymg.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class onJoin implements Listener {

    @EventHandler
    public void onVipJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (p.hasPermission("join.vip")) {

            e.setJoinMessage("§e» §6§l! §e« " + ChatColor.GOLD + "§lVIP " + ChatColor.GOLD + p.getName() + ChatColor.YELLOW + " dolaczyl do gry!");
        }
    }

    @EventHandler
    public void onSVipJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (p.hasPermission("join.svip")) {

            e.setJoinMessage("§e» §b§l! §e« " + ChatColor.AQUA + "§lSVIP " + ChatColor.AQUA + p.getName() + ChatColor.YELLOW + " dolaczyl do gry!");
        }
    }

    @EventHandler
    public void onCeoJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (p.hasPermission("join.ceo")) {

            e.setJoinMessage("§e» §c§l! §e« " + ChatColor.RED + "§lCEO " + ChatColor.RED + p.getName() + ChatColor.YELLOW + " dolaczyl do gry!");
        }
    }
}

