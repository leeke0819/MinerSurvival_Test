package org.example.code.rpg.Event;

import org.bukkit.Bukkit;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.bukkit.Bukkit.getLogger;

public class PlayerMoveListener implements Listener {

    private HashMap<UUID, BossBar> playerBossBars;
    private Map<UUID, Boolean> playerOnGround;
    private double time;
    private boolean onGround;

    public PlayerMoveListener(HashMap<UUID, BossBar> playerBossBars, Map<UUID, Boolean> playerOnGround) {
        this.playerBossBars = playerBossBars;
        this.playerOnGround = playerOnGround;
        this.onGround = true;
        this.time = 0.0001;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        double y = event.getPlayer().getLocation().getY();

        Player player = event.getPlayer();
        BossBar bossBar = playerBossBars.get(player.getUniqueId());
        if(y < 60) {
            if(time > 1) {
                player.setHealth(0);
            }
            bossBar.setProgress(1-time);
            time += 0.001;
            bossBar.setVisible(true);
            onGround = false;
            //getLogger().info("1");
        } else {
            onGround = true;
            time = 0;
            bossBar.setProgress(1);
            bossBar.setVisible(false);
            //getLogger().info("2");
        }
    }

//    private void startTimer(UUID playerUUID, BossBar bossBar) {
//        BukkitRunnable timerTask = new BukkitRunnable() {
//            int secondsPassed = 0;
//
//            @Override
//            public void run() { //while문과 비슷함
//                secondsPassed++;
//                double progress = (double) secondsPassed / 1000.0; // 100초 기준으로 진행률 계산
//                bossBar.setProgress(progress);
//
//                if (secondsPassed >= 100) {
//                    bossBar.setVisible(false);
//                    cancel(); // 타이머 종료
//                }
//            }
//        };
//        timerTask.runTaskTimer(Bukkit.getPluginManager().getPlugin("RPG"), 0L, 1L);
//    }
//
//    private void stopTimer(UUID playerUUID) {
//        playerBossBars.remove(playerUUID);
//    }
}
