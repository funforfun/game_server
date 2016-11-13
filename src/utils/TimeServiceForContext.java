package utils;

import base.Context;

import java.util.Timer;
import java.util.TimerTask;

public class TimeServiceForContext {

    private Timer timer = null;

    public static TimeServiceForContext getFromContext(Context context) {
        return (TimeServiceForContext) context.get(TimeServiceForContext.class);
    }

    public TimeServiceForContext(Context context) {
        context.add(this.getClass(), this);
    }

    public void start() {
        timer = new Timer();
    }

    public void stop() {
        if (timer != null) {
            timer.cancel();
        }
    }

    public boolean sheduleTask(TimerTask task, long timeMs) {
        if (timer != null) {
            timer.schedule(task, timeMs);
            return true;
        }
        return false;
    }
}
