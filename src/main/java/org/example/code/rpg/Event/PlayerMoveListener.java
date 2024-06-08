package org.example.code.rpg.Event;

import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;
import java.util.UUID;

import static org.bukkit.Bukkit.getLogger;

public class PlayerMoveListener implements Listener {
    private HashMap<UUID, BossBar> playerBossBars;
    private double time;

    public PlayerMoveListener(HashMap<UUID, BossBar> playerBossBars) {
        this.playerBossBars = playerBossBars;
        this.time = 0.0001;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        double y = event.getPlayer().getLocation().getY();

        Player player = event.getPlayer();
        boolean onGround = true;
        BossBar bossBar = playerBossBars.get(player.getUniqueId());
        if(y < 60) {
            bossBar.setProgress(1-time);
            time += 0.0001;
            bossBar.setVisible(true);
            onGround = false;
            getLogger().info("1");
        } else {
            onGround = true;
            bossBar.setProgress(1);
            bossBar.setVisible(false);
            getLogger().info("2");
        }
    }
}
