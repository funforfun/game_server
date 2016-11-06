package base;

import gameMechanics.GameSession;

public interface Frontend extends Abonent {

    void setId(String name, Integer id);

    void addWaitingGameSession(GameSession gameSession);

    void addRunningGameSessions(GameSession gameSession);

    void addFinishedGameSession(GameSession gameSession);
}
