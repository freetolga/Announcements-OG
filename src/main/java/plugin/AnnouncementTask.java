package plugin;

import net.trueog.utilitiesog.UtilitiesOG;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class AnnouncementTask extends BukkitRunnable {

    private List<String> announcements;
    private int index = 0;
    private int lastIndex;
    private long interval;

    AnnouncementTask(List<String> announcements, long interval) {

        this.announcements = announcements;
        this.interval = interval;
        this.lastIndex = announcements.size();

    }

    @Override
    public void run() {

        Bukkit.broadcast(UtilitiesOG.trueogExpand(announcements.get(index)));
        if (index < lastIndex - 1)
            index += 1;
        else
            index = 0;

    }

}