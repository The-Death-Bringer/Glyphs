package me.dthb.glyph;

import com.google.common.collect.Maps;
import org.bukkit.util.Vector;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Map;

public class Glyph {

    private final Map<Vector, Color> pixels = Maps.newHashMap();
    private final double size;
    private final int height;
    private final int width;

    public Glyph(BufferedImage img) {
        width = img.getWidth();
        height = img.getHeight();
        size = 1;

        process(img);
        center();
    }

    private void process(BufferedImage img) {
        for (int x = 0; x < img.getWidth(); x++)
            for (int y = 0; y < img.getHeight(); y++) {
                Color pixel = new Color(img.getRGB(x, y), true);
                if (pixel.getAlpha() == 0)
                    continue;
                pixels.put(new Vector(width - x, height - y, 0), pixel);
            }

        pixels.keySet().forEach(v -> {
            double divx = width / size;
            double divy = height / size;
            v.setX(v.getX() / divx).setY(v.getY() / divy);
        });
    }

    public Map<Vector, Color> getPixels() {
        return Maps.newHashMap(pixels);
    }

    public void center() {
        double offset = (width / 2D) / (width / size);
        pixels.keySet().forEach(v -> v.setX(v.getX() - offset));
    }

}
