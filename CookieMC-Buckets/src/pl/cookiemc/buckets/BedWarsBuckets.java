package pl.cookiemc.buckets;

import com.andrei1058.bedwars.BedWars;
import com.andrei1058.bedwars.api.arena.GameState;
import com.andrei1058.bedwars.api.arena.IArena;
import com.andrei1058.bedwars.api.arena.generator.IGenerator;
import com.andrei1058.bedwars.api.arena.team.ITeam;
import com.andrei1058.bedwars.api.configuration.ConfigManager;
import com.andrei1058.bedwars.api.language.Language;
import com.andrei1058.bedwars.api.language.Messages;
import com.andrei1058.bedwars.api.server.ServerType;
import com.andrei1058.bedwars.arena.Arena;
import com.andrei1058.bedwars.listeners.BreakPlace;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class BedWarsBuckets extends JavaPlugin implements Listener {
    public void onEnable() {
        PlayerBucketEmptyEvent.getHandlerList().unregister((Plugin)JavaPlugin.getProvidingPlugin(BedWars.class));
        getServer().getPluginManager().registerEvents(this, (Plugin)this);
    }

    @EventHandler(ignoreCancelled = true)
    public void onBucketEmpty(PlayerBucketEmptyEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlockClicked();
        World world = player.getLocation().getWorld();
        if (world == null)
            return;
        if (BedWars.getServerType() == ServerType.MULTIARENA && isWorld(world) && !BreakPlace.isBuildSession(player))
            event.setCancelled(true);
        IArena worldArena = Arena.getArenaByIdentifier(world.getName());
        if (worldArena != null && worldArena.getStatus() != GameState.playing) {
            event.setCancelled(true);
            return;
        }
        IArena arena = Arena.getArenaByPlayer(player);
        if (arena == null)
            return;
        if (arena.isSpectator(player)) {
            event.setCancelled(true);
            return;
        }
        if (arena.getRespawnSessions().containsKey(player)) {
            event.setCancelled(true);
            return;
        }
        if (arena.getStatus() != GameState.playing) {
            event.setCancelled(true);
            return;
        }
        ConfigManager cfg = arena.getConfig();
        if (block.getLocation().getBlockY() >= cfg.getInt("max-build-y")) {
            event.setCancelled(true);
            return;
        }
        for (ITeam t : arena.getTeams()) {
            if (t.getSpawn().distanceSquared(block.getLocation()) <= Math.pow(cfg.getInt("spawn-protection"), 2.0D)) {
                player.sendMessage(Language.getMsg(player, Messages.INTERACT_CANNOT_PLACE_BLOCK));
                event.setCancelled(true);
                return;
            }
            if (t.getShop().distanceSquared(block.getLocation()) <= Math.pow(cfg.getInt("shop-protection"), 2.0D)) {
                player.sendMessage(Language.getMsg(player, Messages.INTERACT_CANNOT_PLACE_BLOCK));
                event.setCancelled(true);
                return;
            }
            if (t.getTeamUpgrades().distanceSquared(block.getLocation()) <= Math.pow(cfg.getInt("upgrades-protection"), 2.0D)) {
                player.sendMessage(Language.getMsg(player, Messages.INTERACT_CANNOT_PLACE_BLOCK));
                event.setCancelled(true);
                return;
            }
            for (IGenerator generator : t.getGenerators()) {
                if (generator.getLocation().distanceSquared(block.getLocation()) <= 1.0D) {
                    player.sendMessage(Language.getMsg(player, Messages.INTERACT_CANNOT_PLACE_BLOCK));
                    event.setCancelled(true);
                    return;
                }
            }
        }
        for (IGenerator generator : arena.getOreGenerators()) {
            if (generator.getLocation().distanceSquared(block.getLocation()) <= 1.0D) {
                player.sendMessage(Language.getMsg(player, Messages.INTERACT_CANNOT_PLACE_BLOCK));
                event.setCancelled(true);
                return;
            }
        }
    }

    private static boolean isWorld(World world) {
        return world.getName().equalsIgnoreCase(BedWars.getLobbyWorld());
    }
}