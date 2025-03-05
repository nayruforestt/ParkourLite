package com.nayruforest.parkourlite.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.nayruforest.parkourlite.ParkourLite;

public class StartCommand implements CommandExecutor {

    private final ParkourLite plugin;

    public StartCommand(ParkourLite plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }

        Player player = (Player) sender;
        Location startLocation = plugin.startingLocation();

        if (startLocation == null) {
            player.sendMessage("§c§l[!] §cStart location is not set.");
            return false;
        }

        player.teleport(startLocation);
        player.sendMessage("§a§l[!] §aParkour started! Reach the diamond block to finish.");
        plugin.addingPlayer(player); // Add player to parkour
        return true;
    }
}