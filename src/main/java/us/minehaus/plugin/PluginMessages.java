package us.minehaus.plugin;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.configuration.ConfigurationSection;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PluginMessages {

    private final ConfigurationSection messageConfig;

    private final Map<String, Component> cachedSimpleMessages = new ConcurrentHashMap<>();

    public PluginMessages(Main plugin) {
        this.messageConfig = plugin.getConfig().getConfigurationSection("messages");
    }

    // Command messages

    public Component noPermissionLocateOutpost() {
        return simple("command.locate-outpost.no-permission");
    }

    public Component located(String outpost) {
        return MiniMessage.miniMessage().deserialize(messageConfig.getString("command.locate-outpost.located"),
                Placeholder.unparsed("outpost", outpost)
        );
    }

    private Component simple(String key) {
        Component message = cachedSimpleMessages.get(key);

        if (message == null) {
            message = MiniMessage.miniMessage().deserialize(messageConfig.getString(key));
            cachedSimpleMessages.put(key, message);
        }

        return message;
    }

    public Component noLocate() {
        return simple("command.locate-outpost.no-locate");
    }
}
