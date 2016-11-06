package gameMechanics;

import frontend.UserSession;

import java.util.ArrayList;
import java.util.List;

public class GameSession {

    private static int STARTED = 1;
    private static int FINISHED = 2;
    private int status = STARTED;
    private List<UserSession> players = new ArrayList<UserSession>();

    public void addPlayer(UserSession user) {
        players.add(user);
    }

    public void finish() {
        status = FINISHED;
    }

    public boolean isFinished() {
        return status == FINISHED;
    }

}
