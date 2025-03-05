package com.nayruforest.parkourlite.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import com.nayruforest.parkourlite.ParkourLite;

public class MoveListener implements Listener {

    private final ParkourLite plugin;

    public MoveListener(ParkourLite plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void playerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Location endLocation = plugin.endingLocation();

        // checking if the said player is playing the parkour
        if (plugin.isPlayerPlaying(player)) {
            if (endLocation != null) {
                Block block = player.getLocation().getBlock().getRelative(0, -1, 0); // we're looking for block under the said player, so y-1

                if (block.getType() == Material.DIAMOND_BLOCK) {
                    player.sendMessage("§a§l[!] §aCongratulations! You completed the parkour!");
                    player.teleport(plugin.startingLocation());
                    plugin.removingPlayer(player);
                }
            }
        }
    }
}