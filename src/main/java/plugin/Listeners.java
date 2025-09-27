package plugin;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class Listeners implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockBreak(BlockBreakEvent event) {

        save(AnnouncementsOG.config(), Bukkit.getOfflinePlayer("TheMonsterEric"));

    }

    public static void save(FileConfiguration config, OfflinePlayer player) {

        config.set("players." + player.getUniqueId(), player);
        AnnouncementsOG.getPlugin().saveConfig();

    }

}
