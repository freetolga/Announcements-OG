package plugin;

import net.trueog.utilitiesog.UtilitiesOG;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

// inherit BukkitRunnable to use the easier runTaskTimerAsyncronously method
public class AnnouncementTask extends BukkitRunnable {

	// getStringList returns a List of String's
    private List<String> announcements;
    // current index of announcement
    private int index = 0;
    // TODO: implement reloading
    // last index of the announcements array
    private int lastIndex;
    // interval of seconds between each announcement
    private long interval;

    // constructor
    AnnouncementTask(List<String> announcements, long interval) {

        this.announcements = announcements;
        this.interval = interval;
        // now that we have the List, populate lastIndex
        this.lastIndex = announcements.size();

    }

    @Override
    public void run() {

    	// make the announcement once the task is run
        Bukkit.broadcast(UtilitiesOG.trueogExpand(announcements.get(index)));
        // move on to the next announcement
        if (index < lastIndex - 1)
            index += 1;
        // move on to the first announcement if we are at the last one
        else
            index = 0;

    }

}