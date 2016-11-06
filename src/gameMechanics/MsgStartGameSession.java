package gameMechanics;

import base.Address;
import base.GameMechanics;
import frontend.UserSession;
import messageSystem.MessageToGameMechanics;

public class MsgStartGameSession extends MessageToGameMechanics {
    private UserSession user1;
    private UserSession user2;

    public MsgStartGameSession(Address from, Address to, UserSession user1, UserSession user2) {
        super(from, to);
        this.user1 = user1;
        this.user2 = user2;
    }

    public void exec(GameMechanics gameMechanics) {
        // TODO все данные о партии игроков (их id, карта, положение фигур)
        GameSession gameSession = new GameSession();
        gameSession.addPlayer(user1);
        gameSession.addPlayer(user2);


        // TODO: сообщение на фронтенд: gameStarted!

    }
}
