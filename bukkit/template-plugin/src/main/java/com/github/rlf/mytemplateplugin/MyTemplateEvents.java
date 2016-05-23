package com.github.rlf.mytemplateplugin;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;

/**
 * Simpel Listener der fyrer en ildkugle afsted når en spiller klikker med en pil.
 */
public class MyTemplateEvents implements Listener {

    private final double fireball_speed;

    public MyTemplateEvents(FileConfiguration config) {
        // Default værdien er 1.0, men kan overstyres i config.yml, hvis man vil.
        fireball_speed = config.getDouble("fireball.speed", 1d);
    }

    /**
     * @see @link https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/event/player/PlayerInteractEvent.html
     */
    @EventHandler
    public void onFireGesture(PlayerInteractEvent e) {
        if (e.isCancelled() || e.getPlayer() == null || e.getItem() == null) {
            // Lad være med at spilde tid på det så
            return;
        }
        Player player = e.getPlayer();
        if (e.getItem().getType() == Material.ARROW) {
            Location eyeLocation = player.getEyeLocation();
            // TODO: 23/05/2016 - R4zorax: Måske når ildkuglen at skade spilleren?
            Fireball fireball = e.getPlayer().getWorld().spawn(eyeLocation, Fireball.class);
            fireball.setBounce(true);
            fireball.setIsIncendiary(true);
            fireball.setCustomName(player.getDisplayName() + "'s ildkugle");
            fireball.setCustomNameVisible(true);
            fireball.setShooter(player);
            Vector retning = eyeLocation.getDirection();
            fireball.setVelocity(retning.multiply(fireball_speed));
            e.setCancelled(true); // Undlad at "slå med pilen", det klarer ildkuglen.
        }
    }
}
