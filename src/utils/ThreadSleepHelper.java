package utils;

public class ThreadSleepHelper {
    private static int DEFAULT_SLEEP_TIME = 5000;
    public static int SHORT_SLEEP_TIME = 10;

    public static void sleep() {
        try {
            Thread.sleep(DEFAULT_SLEEP_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void sleep(int period) {
        try {
            Thread.sleep(period);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
