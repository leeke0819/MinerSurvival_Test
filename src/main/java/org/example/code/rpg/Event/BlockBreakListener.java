package org.example.code.rpg.Event;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.example.code.rpg.Manager.JobConfigManager;
import org.example.code.rpg.RPG;

import static org.bukkit.Bukkit.getLogger;

public class BlockBreakListener implements Listener {
    private RPG plugin;
    public BlockBreakListener(RPG plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        Material blockType = block.getType();
        Player player = event.getPlayer();
        player.sendMessage(String.valueOf(blockType));
        //Math.random();
        JobConfigManager jobConfigManager = new JobConfigManager(plugin);
        String[] a = jobConfigManager.getPlayerJob(player).split(",");
        String job = a[0];
        String level = a[1];
        getLogger().info(job + level);
        if (job.equals("§6§l나무꾼") && level.equals("1차")) {
            if(blockType == Material.OAK_LOG) { // 나무 더 추가하기
                int c = (int)(Math.random()*100);
                getLogger().info("a");
                if(0 < c && c <= 10){
                    ItemStack itemStack = new ItemStack(Material.OAK_LOG, 10);
                    player.getInventory().addItem(itemStack);
                    getLogger().info("b");
                }
            }
        }
    }
}
