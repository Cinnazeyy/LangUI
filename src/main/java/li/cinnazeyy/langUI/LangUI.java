package li.cinnazeyy.langUI;

import li.cinnazeyy.langUI.commands.CMD_Language;
import li.cinnazeyy.langUI.util.LangUtil;
import li.cinnazeyy.langlibs.core.file.YamlFileFactory;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.ipvp.canvas.MenuFunctionListener;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import static net.kyori.adventure.text.Component.text;

public final class LangUI extends JavaPlugin {

    private static LangUI plugin;
    private YamlConfiguration config;

    public static LangUI getPlugin() {
        return plugin;
    }
    @Override
    public void onEnable() {
        // Plugin startup login
        plugin = this;

        YamlFileFactory.registerPlugin(this);

        // Register Events
        getServer().getPluginManager().registerEvents(new MenuFunctionListener(), plugin);

        // Register Commands
        final CommandMap commandMap = Bukkit.getServer().getCommandMap();
        commandMap.register("LangUI", new CMD_Language("language"));

        // Load config
        createConfig();

        // Load language files
        try {
            LangUtil.init();
            getLogger().severe("Successfully loaded language files.");
        } catch (Exception ex) {
            getPlugin().getComponentLogger().error(text(ex.getMessage()), ex);
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
    }

    private void createConfig() {
        File createConfig = new File(getDataFolder(), "config.yml");
        if (!createConfig.exists()) {
            createConfig.getParentFile().mkdirs();
            saveResource("config.yml", false);
        }

        config = new YamlConfiguration();
        try {
            config.load(createConfig);
        } catch (IOException | InvalidConfigurationException e) {
            getLogger().log(Level.SEVERE,e.getMessage());
        }
    }
}
