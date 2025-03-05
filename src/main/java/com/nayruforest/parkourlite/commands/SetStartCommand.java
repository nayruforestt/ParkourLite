package com.nayruforest.parkourlite.commands;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.nayruforest.parkourlite.ParkourLite;

public class SetStartCommand implements CommandExecutor {

    private final ParkourLite plugin;

    public SetStartCommand(ParkourLite plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }

        Player player = (Player) sender;
        Block block = player.getLocation().getBlock().getRelative(0, -1, 0); // checking a block under the person, meaning y=y-1

        if (block.getType() == Material.GRASS) { // if the player is standing on grass while the command is being executed
            Location location = player.getLocation();
            plugin.startingLocation(location);
            player.sendMessage("§a§l[!] §aStart location set!");
            return true;
        } else {
            player.sendMessage("§c§l[!] §cYou are not standing on the grass.");
            return false;
        }
    }
}