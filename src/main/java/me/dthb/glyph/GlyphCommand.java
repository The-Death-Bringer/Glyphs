package me.dthb.glyph;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class GlyphCommand extends Command {

    public GlyphCommand() {
        super("glyph");
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {

        if (!(sender instanceof Player player))
            return false;

        if (args.length != 1) {
            sender.sendMessage(Component.text("Error, no link provided", NamedTextColor.RED));
            return false;
        }

        try {
            BufferedImage image = ImageIO.read(new URL(args[0]));
            Glyph glyph = new Glyph(image);
            new GlyphRunner(player, glyph).runGlyph();
            sender.sendMessage(Component.text("Running glyph", NamedTextColor.GREEN));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

}
