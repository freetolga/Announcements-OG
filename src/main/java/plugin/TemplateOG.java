package plugin;

import net.trueog.diamondbankog.DiamondBankAPIJava;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class TemplateOG extends JavaPlugin {

	private static TemplateOG plugin;
	private static DiamondBankAPIJava diamondBankAPI;

	public void onEnable() {
		plugin = this;

		getServer().getPluginManager().registerEvents(new Listeners(), this);

		RegisteredServiceProvider<DiamondBankAPIJava> diamondBankAPIProvider = getServer().getServicesManager().getRegistration(DiamondBankAPIJava.class);
		if (diamondBankAPIProvider == null) {
			getLogger().severe("DiamondBank-OG API is null");
			Bukkit.getPluginManager().disablePlugin(this);
			return;
		}
		diamondBankAPI = diamondBankAPIProvider.getProvider();
	}

	public static TemplateOG getPlugin() {
		return plugin;
	}

	public static DiamondBankAPIJava diamondBankAPI() {
		return diamondBankAPI;
	}
}