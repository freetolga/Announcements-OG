// This is free and unencumbered software released into the public domain.
// Author: freetolga.
package plugin;

import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class AnnouncementsOG extends JavaPlugin {

    private static AnnouncementsOG plugin;
    // store the pointer to the task to prepare for reloading support
    private static AnnouncementTask announcementTask;
    private static FileConfiguration config;
    
    // declare these inside the class to change them later when reloading is added
    // might be reorganized later
    private static long timeInterval;
    private static List<String> announcements;

    @Override
    public void onEnable() {

    	// get a pointer to the main plugin class to pass into other methods later
        plugin = this;

        // initialize config with default value if it doesnt exist yet
        saveDefaultConfig();

        // load the configuration from the server otherwise
        config = getConfig();

        // get the value of these config.yml keys
        announcements = config.getStringList("announcements");
        timeInterval = config.getInt("timeInterval");

        // populate the pointer to the task by creating an instance of it
        announcementTask = new AnnouncementTask(announcements, timeInterval);

        // call the method on the task to run it
        announcementTask.runTaskTimerAsynchronously(
        		// instance of our plugin
        		plugin,
        		// run every timeInterval seconds
        		timeInterval * 20,
        		// run for(or wait depending on how you look at it) every timeInterval seconds
        		// before starting the task again
        		timeInterval * 20);

    }

    // TODO: fix onDisable and onReload
    @Override
    public void onDisable() {

        announcementTask.cancel();

    }

    // boilerplate
    public static AnnouncementsOG getPlugin() {

        return plugin;

    }

    // boilerplate
    public static FileConfiguration config() {

        return config;

    }

}
