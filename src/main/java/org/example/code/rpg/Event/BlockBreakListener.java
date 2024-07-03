package org.example.code.rpg.Event;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.example.code.rpg.Manager.JobConfigManager;
import org.example.code.rpg.RPG;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.bukkit.Bukkit.getLogger;

public class BlockBreakListener implements Listener {
    private RPG plugin;
    private Map<UUID, Double> playerO2 = new HashMap<>();
    public BlockBreakListener(RPG plugin, Map<UUID, Double> playerO2){
        this.plugin = plugin;
        this.playerO2 = playerO2;
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
        if (job.equals("§7§l광부") && level.equals("1차")) {
            if(blockType == Material.COAL_ORE) {
                int c = (int)(Math.random()*100);
                if(0 < c && c <= 10){
                    ItemStack itemStack = new ItemStack(Material.COAL, 5);
                    player.getInventory().addItem(itemStack);
                    player.sendMessage("석탄을 5개 더 얻었습니다!");
                }
            }
        }
        double time = playerO2.get(player.getUniqueId());
        switch (blockType) {
            case COAL_ORE:
                time -= 0.1;
                playerO2.put(player.getUniqueId(), time);
                player.sendMessage("석탄 광석을 부쉈습니다!");
                break;
            case COPPER_ORE:
                player.sendMessage("구리 광석을 부쉈습니다!");
                break;
            case IRON_ORE:
                player.sendMessage("철 광석을 부쉈습니다!");
                break;
            case GOLD_ORE:
                player.sendMessage("금 광석을 부쉈습니다!");
                break;
            case REDSTONE_ORE:
                player.sendMessage("레드스톤 광석을 부쉈습니다!");
                break;
            case LAPIS_ORE:
                player.sendMessage("청금석 광석을 부쉈습니다!");
                break;
            case EMERALD_ORE:
                player.sendMessage("에메랄드 광석을 부쉈습니다!");
                break;
            case DIAMOND_ORE:
                player.sendMessage("다이아몬드 광석을 부쉈습니다!");
                break;
            case AMETHYST_CLUSTER:
                player.sendMessage("자수정 결정을 부쉈습니다!");
                break;
            case NETHER_GOLD_ORE:
                player.sendMessage("네더 금 광석을 부쉈습니다!");
                break;
            case NETHER_QUARTZ_ORE:
                player.sendMessage("네더 석영 광석을 부쉈습니다!");
                break;
            case ANCIENT_DEBRIS:
                player.sendMessage("고대 잔해를 부쉈습니다!");
                break;
        }
    }
}