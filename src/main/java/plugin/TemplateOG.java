package plugin;

import org.bukkit.plugin.java.JavaPlugin;

public class TemplateOG extends JavaPlugin {

	private static TemplateOG plugin;
	private static DiamondBankAPI diamondBankAPI;
	
	public void onEnable() {
		plugin = this;

		getServer().getPluginManager().registerEvents(new Listeners(), this);
		
		RegisteredServiceProvider<DiamondBankAPI> diamondBankAPIProvider = getServer().getServicesManager().getRegistration(DiamondBankAPI.class);
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

	public static DiamondBankAPI diamondBankAPI() {
		return diamondBankAPI;
	}
}