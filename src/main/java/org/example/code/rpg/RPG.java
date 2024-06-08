package org.example.code.rpg;

import org.bukkit.boss.BossBar;
import org.bukkit.plugin.java.JavaPlugin;
import org.example.code.rpg.Command.*;
import org.example.code.rpg.Event.BlockBreakListener;
import org.example.code.rpg.Event.PlayerJoinListener;
import org.example.code.rpg.Event.PlayerMoveListener;
import org.example.code.rpg.Event.RightClickListener;
import org.example.code.rpg.Manager.JobConfigManager;

import java.util.HashMap;
import java.util.UUID;


public final class RPG extends JavaPlugin {
    private JobConfigManager jobConfigManager;
    private HashMap<UUID, BossBar> playerBossBars = new HashMap<>();
    @Override
    public void onEnable() {
        getLogger().info("RPG Plugin이 적용되었습니다.");
        this.saveDefaultConfig();
        this.getCommand("광부").setExecutor(new JobCommand(this));
        this.getCommand("나무꾼").setExecutor(new JobCommand(this));
        this.getCommand("농부").setExecutor(new JobCommand(this));
        this.getCommand("사냥꾼").setExecutor(new JobCommand(this));
        this.getCommand("어부").setExecutor(new JobCommand(this));
        this.getCommand("연금술사").setExecutor(new JobCommand(this));
        this.getCommand("돈").setExecutor(new MoneyCommand(this));
        getServer().getPluginManager().registerEvents(new RightClickListener(this), this);
        this.jobConfigManager = new JobConfigManager(this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this, playerBossBars), this);
        getServer().getPluginManager().registerEvents(new BlockBreakListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerMoveListener(playerBossBars), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("RPG Plugin 적용이 해제되었습니다.");
        this.saveDefaultConfig();
    }

    public JobConfigManager getJobConfig() {
        return jobConfigManager;
    }

}
