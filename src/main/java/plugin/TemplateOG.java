package plugin;

import org.bukkit.plugin.java.JavaPlugin;

public class TemplateOG extends JavaPlugin {

	private static TemplateOG plugin;
	public void onEnable() {

		plugin = this;

		getServer().getPluginManager().registerEvents(new Listeners(), this);

	}

	public static TemplateOG getPlugin() {

		return plugin;

	}

}