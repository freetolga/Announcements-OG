// This is free and unencumbered software released into the public domain.
// Author: freetolga.
package plugin;

import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class AnnouncementsOG extends JavaPlugin {

    private static AnnouncementsOG plugin;
    // store the pointer to the task to prepare for reloading support
    private AnnouncementManager announcementManager;
    private static FileConfiguration config;

    // declare these inside the class to change them later when reloading is added
    // might be reorganized later
    private long interval;
    private List<String> announcements;

    public AnnouncementsOG() {

        plugin = this;
        saveDefaultConfig();
        config = getConfig();
        announcements = config.getStringList("announcements");
        interval = config.getLong("timeInterval");
        announcementManager = new AnnouncementManager(announcements, interval, 0);

    }

    @Override
    public void onEnable() {

        // get a pointer to the main plugin class to pass into other methods later

        announcementManager.start();

    }

    // TODO: fix onDisable and onReload
    @Override
    public void onDisable() {

        announcementManager.stop();

    }

    @Override
    public void reloadConfig() {

        this.announcements = config.getStringList("announcements");
        this.interval = config.getLong("timeInterval");
        announcementManager.Announcements = this.announcements;
        announcementManager.timeInterval = this.interval;
        announcementManager.reload(0);

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
