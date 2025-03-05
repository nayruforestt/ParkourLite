package com.nayruforest.parkourlite;

import com.nayruforest.parkourlite.commands.SetEndCommand;
import com.nayruforest.parkourlite.commands.SetStartCommand;
import com.nayruforest.parkourlite.commands.StartCommand;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import com.nayruforest.parkourlite.listeners.MoveListener;

import java.util.HashSet;
import java.util.Set;

public final class ParkourLite extends JavaPlugin {

    private Location startLocation;
    private Location endLocation;
    private final Set<Player> playersPlaying = new HashSet<>();

    @Override
    public void onEnable() {
        this.getCommand("setstart").setExecutor(new SetStartCommand(this));
        this.getCommand("setend").setExecutor(new SetEndCommand(this));
        this.getCommand("start").setExecutor(new StartCommand(this));
        getServer().getPluginManager().registerEvents(new MoveListener(this), this);
    }

    @Override
    public void onDisable() {
    }

    public Location startingLocation() {
        return startLocation;
    }

    public void startingLocation(Location startLocation) {
        this.startLocation = startLocation;
    }

    public Location endingLocation() {
        return endLocation;
    }

    public void endingLocation(Location endLocation) {
        this.endLocation = endLocation;
    }

    public void addingPlayer(Player player) {
        playersPlaying.add(player);
    }

    public void removingPlayer(Player player) {
        playersPlaying.remove(player);
    }

    public boolean isPlayerPlaying(Player player) {
        return playersPlaying.contains(player);
    }
}