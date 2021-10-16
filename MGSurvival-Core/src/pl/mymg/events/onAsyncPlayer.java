package pl.mymg.events;

import org.apache.commons.lang.StringUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class onAsyncPlayer implements Listener {

    @EventHandler
    public void onAsyncPlayerChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        if (p.hasPermission("cmc.emotki")) {
                e.setMessage(e.getMessage()
                        .replaceAll(":lenny:", "( ͡° ͜ʖ ͡°)")
                        .replaceAll(":calus:", "˶⚈Ɛ⚈˵"));
        }
    }
}

