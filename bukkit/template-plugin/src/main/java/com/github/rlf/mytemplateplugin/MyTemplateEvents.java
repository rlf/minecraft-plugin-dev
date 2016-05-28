package com.github.rlf.mytemplateplugin;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

/**
 * Simpel Listener der fyrer en ildkugle afsted når en spiller klikker med en pil.
 */
public class MyTemplateEvents implements Listener {

    private int fireball_yield;
    private double speed;

    public MyTemplateEvents(FileConfiguration config) {
        // Default værdien er 3, men kan overstyres i config.yml, hvis man vil.
        fireball_yield = config.getInt("fireball.yield", 3);
        speed = config.getDouble("fireball.speed", 0.5d);
    }

    /**
     * @see @link https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/event/player/PlayerInteractEvent.html
     */
    @EventHandler
    public void onFireGesture(PlayerInteractEvent e) {
        // BEMÆRK: e.isCancelled() er true, hvis eventen ikke gør noget i Vanilla
        //         f.eks. hvis man slår ud i luften
        if (e.getPlayer() == null || e.getHand() != EquipmentSlot.HAND || e.getItem() == null) {
            // Lad være med at spilde tid på det så
            return;
        }
        // Spilleren brugte sin HAND (ikke off-hand).
        Player player = e.getPlayer();
        ItemStack itemInHand = e.getItem();
        if (itemInHand !=  null && itemInHand.getType() == Material.ARROW) {
            Location eyeLocation = player.getEyeLocation();
            Fireball fireball = e.getPlayer().getWorld().spawn(eyeLocation, Fireball.class);
            fireball.setBounce(true);
            fireball.setIsIncendiary(true);
            fireball.setCustomName(player.getDisplayName() + "'s ildkugle");
            fireball.setCustomNameVisible(true);
            fireball.setShooter(player);
            fireball.setYield(fireball_yield);
            e.setCancelled(true); // Undlad at "slå med pilen", det klarer ildkuglen.
        }
    }
}
