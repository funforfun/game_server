package base;

import frontend.UserSession;
import gameMechanics.GameSession;

public interface GameMechanics extends Abonent {
    MessageSystem getMessageSystem();

    void addUserToGameSession(UserSession userSession, GameSession gameSession);

    GameSession createGameSession(UserSession userSession);

    void addGameSession(GameSession gameSession);
}
