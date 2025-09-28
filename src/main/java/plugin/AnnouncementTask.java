package plugin;

import net.trueog.utilitiesog.UtilitiesOG;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

// inherit BukkitRunnable to use the easier runTaskTimerAsyncronously method
public class AnnouncementTask extends BukkitRunnable {

    // getStringList returns a List of String's
    private List<String> announcements;
    // current index of announcement
    private int index = 0;

    // constructor
    AnnouncementTask(@NotNull List<String> announcements, @NotNull int index) {

        this.announcements = announcements;
        this.index = index;

    }

    public void setIndex(int newIndex) {

        this.index = newIndex;

    }

    public void setAnnouncements(List<String> announcements) {

        this.announcements = announcements;

    }

    @Override
    public void run() {

        // make the announcement once the task is run
        Bukkit.broadcast(UtilitiesOG.trueogExpand(announcements.get(index)));
        // increment or loop over the index
        if (index < announcements.size() - 1) {

            index += 1;

        } else {

            index = 0;

        }

    }

}