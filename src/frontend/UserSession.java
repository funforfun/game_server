package frontend;

import java.util.concurrent.atomic.AtomicInteger;

public class UserSession {
    private static AtomicInteger currentSessionId = new AtomicInteger(1);
    private int sessionId;
    private String name;
    private long userId;

    UserSession() {
        sessionId = currentSessionId.getAndIncrement();
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
