// This is free and unencumbered software released into the public domain.
// Author: NotAlexNoyle.
package plugin;

import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class AnnouncementsOG extends JavaPlugin {

    private static AnnouncementsOG plugin;
    private static AnnouncementTask announcementTask;
    private static FileConfiguration config;
    private static long timeInterval;
    private static List<String> announcements;

    @Override
    public void onEnable() {

        plugin = this;

        saveDefaultConfig();
        config = getConfig();

        announcements = config.getStringList("announcements");
        timeInterval = config.getInt("timeInterval");

        announcementTask = new AnnouncementTask(announcements, timeInterval);
        announcementTask.runTaskTimerAsynchronously(plugin, timeInterval * 20, timeInterval * 20);

    }

    @Override
    public void onDisable() {

        announcementTask.cancel();

    }

    public static AnnouncementsOG getPlugin() {

        return plugin;

    }

    public static FileConfiguration config() {

        return config;

    }

}
