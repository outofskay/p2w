package pl.cookiemc.doublejump;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public class DJumpPlugin extends JavaPlugin implements Listener {
    public void onEnable() {
        this.getLogger().info("Copyright CookieMC 2021/2022");
    }

    @EventHandler
    public void onGroundCheck(PlayerMoveEvent e) {
        if (e.getPlayer().isOnGround())
            e.getPlayer().setAllowFlight(true);
    }

    @EventHandler
    public void onFlyAndJump(PlayerToggleFlightEvent e) {
        Player p = e.getPlayer();
        if (p.hasPermission("double.vip")) {
            if (!p.getGameMode().equals(GameMode.CREATIVE)) {
                p.getWorld().playSound(p.getLocation(), Sound.BAT_TAKEOFF, 1.0F, 1.0F);
                e.getPlayer().getWorld().playEffect(e.getPlayer().getLocation(), Effect.EXPLOSION, 8);
                e.getPlayer().getWorld().playEffect(e.getPlayer().getLocation(), Effect.MOBSPAWNER_FLAMES, 8);
                e.setCancelled(true);
                p.setAllowFlight(false);
                p.setVelocity(new Vector(0.0D, 2.2D, 0.0D));
                p.setVelocity(p.getLocation().getDirection().multiply(1.5D));
            }
        } else {
            p.sendMessage(ChatColor.GRAY + "Aby uzyc DoubleJumpa musisz posiadac range" + ChatColor.GREEN + " VIP" + ChatColor.GRAY + "!");
            p.sendMessage(ChatColor.GRAY + "Zakup ja na Naszej stronie" + ChatColor.YELLOW + "www.cookiemc.pl" + ChatColor.GRAY + "!");
            e.setCancelled(true);
        }
    }
}
