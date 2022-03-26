package me.dthb.glyph;

import com.destroystokyo.paper.ParticleBuilder;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class GlyphRunner {

    private final Player player;
    private final Glyph glyph;

    public GlyphRunner(Player player, Glyph glyph) {
        this.player = player;
        this.glyph = glyph;
    }

    public void runGlyph() {

        long refresh = 4;
        new BukkitRunnable() {

            int count = 0;
            final int max = 5 * 20;

            @Override
            public void run() {
                if (count == max) {
                    cancel();
                    return;
                }

                Location loc = player.getLocation().clone();
                double yangle = -Math.toRadians(loc.getYaw());

                loc.add(0, 0.6, 0);
                Vector dir = player.getEyeLocation().getDirection().setY(0).multiply(-0.4);
                loc.add(dir);
                glyph.getPixels().forEach((k, v) -> {

                    Vector clon = k.clone().rotateAroundY(yangle);
                    Location particleLoc = loc.clone().add(clon);
                    org.bukkit.Color bukkitColor = org.bukkit.Color.fromRGB(v.getRed(), v.getGreen(), v.getBlue());
                    new ParticleBuilder(Particle.REDSTONE).location(particleLoc).color(bukkitColor, 0.5F).spawn();

                });
                count += refresh;

            }

        }.runTaskTimer(JavaPlugin.getPlugin(GlyphPlugin.class), 0, refresh);
    }

}
