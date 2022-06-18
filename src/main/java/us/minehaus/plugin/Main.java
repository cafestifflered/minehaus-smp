package us.minehaus.plugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import us.minehaus.plugin.commands.LocateOutpostCommand;

public class Main extends JavaPlugin {

    private static Main instance;
    private PluginMessages pluginMessages;


    @Override
    public void onEnable() {
        super.onEnable();
        saveDefaultConfig();
        reloadConfig();
        instance = this;
        this.pluginMessages = new PluginMessages(this);
        Bukkit.getCommandMap().register("minehaus", new LocateOutpostCommand());
    }

    public static Main getInstance() {
        return instance;
    }

    public PluginMessages getPluginMessages() {
        return pluginMessages;
    }
}
