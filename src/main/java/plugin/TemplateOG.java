package plugin;

import net.trueog.diamondbankog.DiamondBankAPIJava;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class TemplateOG extends JavaPlugin {

    private static TemplateOG plugin;
    private static DiamondBankAPIJava diamondBankAPI;
    private static FileConfiguration config;

    @Override
    public void onEnable() {

        plugin = this;

        saveDefaultConfig();
        config = getConfig();

        getServer().getPluginManager().registerEvents(new Listeners(), this);

        RegisteredServiceProvider<DiamondBankAPIJava> provider =
                getServer().getServicesManager().getRegistration(DiamondBankAPIJava.class);

        if (provider == null) {
            getLogger().severe("DiamondBank-OG API is null – disabling plugin.");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }
        diamondBankAPI = provider.getProvider();
    }

    public static TemplateOG getPlugin() {
        return plugin;
    }

    public static DiamondBankAPIJava diamondBankAPI() {
        return diamondBankAPI;
    }

    public static FileConfiguration config() {
        return config;
    }
}
