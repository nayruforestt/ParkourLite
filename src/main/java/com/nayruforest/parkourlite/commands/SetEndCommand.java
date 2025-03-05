package com.nayruforest.parkourlite.commands;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.nayruforest.parkourlite.ParkourLite;

public class SetEndCommand implements CommandExecutor {

    private final ParkourLite plugin;

    public SetEndCommand(ParkourLite plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }

        Player player = (Player) sender;
        Block block = player.getLocation().getBlock().getRelative(0, -1, 0); // again, block under the player, so y=y-1

        if (block.getType() == Material.DIAMOND_BLOCK) { //is the block under a fucking diamond block
            if (plugin.isPlayerPlaying(player)) {
                // is the player in parkour, and we're also considering if the person has reached the end
                player.sendMessage("§a§l[!] §aCongratulations! You completed the parkour!");
                player.teleport(plugin.startingLocation()); // we teleport them back to the start
                plugin.removingPlayer(player); // and we remove the player from the parkour
            } else {
                // setting the end location
                Location location = player.getLocation();
                plugin.endingLocation(location);
                player.sendMessage("§a§l[!] §aEnd location set!");
            }
            return true;
        } else {
            player.sendMessage("§c§l[!] §cYou are not standing on a diamond block..");
            return false;
        }
    }
}