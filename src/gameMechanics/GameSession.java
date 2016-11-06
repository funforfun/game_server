package gameMechanics;

import base.GameMechanics;
import frontend.UserSession;

import java.util.ArrayList;
import java.util.List;

public class GameSession {

    private static int WAITING = 1;
    private static int STARTED = 2;
    private static int FINISHED = 3;
    private int status = WAITING;
    private List<UserSession> players = new ArrayList<UserSession>();
    private GameMechanics gameMechanics;
    private UserSession winner;

    public GameSession(GameMechanics gameMechanics) {
        this.gameMechanics = gameMechanics;
        gameMechanics.addGameSession(this);
    }

    public void addPlayer(UserSession user) {
        gameMechanics.addUserToGameSession(user, this);
        players.add(user);
    }

    public void start() {
        status = STARTED;
    }

    public void finish() {
        status = FINISHED;
    }

    public boolean isWaiting() {
        return status == WAITING;
    }

    public boolean isStarted() {
        return status == STARTED;
    }

    public boolean isFinished() {
        return status == FINISHED;
    }

    public UserSession getFirstPlayer() {
        return players.get(0);
    }

    public void setWinner(UserSession player) {
        if (!players.contains(player)) {
            throw new RuntimeException("Unknown player!");
        }

        winner = player;
        status = FINISHED;
    }
}
