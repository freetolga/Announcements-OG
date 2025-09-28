package plugin;

import java.util.List;

public class AnnouncementManager {

    public List<String> Announcements;
    public long timeInterval;
    public int announcementIndex = 0;
    AnnouncementTask task;

    AnnouncementManager(List<String> announcements, long interval, int index) {

        this.Announcements = announcements;
        this.timeInterval = interval;
        task = new AnnouncementTask(Announcements, announcementIndex);

    }

    void start() {

        task.runTaskTimerAsynchronously(AnnouncementsOG.getPlugin(), this.timeInterval * 20, this.timeInterval * 20);

    }

    void stop() {

        try {

            task.cancel();

        } catch (Exception e) {

        } finally {

            return;

        }

    }

    void reload(int interval) {

        stop();
        task.setIndex(0);
        task.setAnnouncements(this.Announcements);
        start();

    }

}