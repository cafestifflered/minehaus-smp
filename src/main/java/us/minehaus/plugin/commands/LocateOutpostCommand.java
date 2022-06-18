package us.minehaus.plugin.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.*;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import us.minehaus.plugin.Main;
import us.minehaus.plugin.PluginMessages;

public class LocateOutpostCommand extends BukkitCommand {

    public LocateOutpostCommand() {
        super("locate-outpost");
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        PluginMessages pluginMessages = Main.getInstance().getPluginMessages();
        if (!sender.hasPermission("us.minehaus.locate.outpost")) {
            sender.sendMessage(pluginMessages.noPermissionLocateOutpost());
            return true;
        }

        Player player = (Player) sender;
        World world = player.getWorld();

        world.playSound(player, Sound.EVENT_RAID_HORN, 99, 1);

        Location location = world.locateNearestStructure(player.getLocation(), StructureType.PILLAGER_OUTPOST, 100, false);
        if (location == null) {
            sender.sendMessage(pluginMessages.noLocate());
            return true;
        } else {
            sender.sendMessage(pluginMessages.located("[%s, ~, %s]".formatted(location.getX(), location.getZ())));
        }


        return true;
    }
}
