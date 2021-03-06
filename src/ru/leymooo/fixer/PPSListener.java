package ru.leymooo.fixer;

import java.util.WeakHashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import ru.leymooo.fixer.utils.ppsutils.PPSPlayer;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;

public class PPSListener extends PacketAdapter {

    public static WeakHashMap<Player, PPSPlayer> ppsPlayerByPlayer;

    public PPSListener(Main plugin) {
        super(plugin, ListenerPriority.NORMAL, PacketType.Play.Client.getInstance());
        ppsPlayerByPlayer = new WeakHashMap<Player, PPSPlayer>();
    }

    @Override
    public void onPacketReceiving(PacketEvent event) {
        if (event.isCancelled()) return;
        Player p = Bukkit.getPlayerExact(event.getPlayer().getName());
        if (p == null || !p.isOnline()) return;
        if (ppsPlayerByPlayer.containsKey(p)) {
            if (ppsPlayerByPlayer.get(p).handlePPS()) {
                event.setCancelled(true);
                Bukkit.getScheduler().runTask(plugin, new Runnable() {
                    @Override
                    public void run() {
                        p.kickPlayer(plugin.getConfig().getString("max-pps-kick-msg").replace("&", "§"));
                    }
                });
            }
        } else {
            ppsPlayerByPlayer.put(p, new PPSPlayer());
        }
    }
}
