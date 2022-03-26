package me.dthb.glyph;

import org.bukkit.plugin.java.JavaPlugin;

public final class GlyphPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getCommandMap().register("glyph", new GlyphCommand());
    }

}
