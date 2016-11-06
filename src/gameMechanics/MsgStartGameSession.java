package gameMechanics;

import base.Address;
import base.GameMechanics;
import frontend.MessageGameStarted;
import frontend.MessageUpdateUserId;
import frontend.UserSession;
import messageSystem.MessageToGameMechanics;

public class MsgStartGameSession extends MessageToGameMechanics {
    private GameSession gameSession;

    public MsgStartGameSession(Address from, Address to, GameSession gameSession) {
        super(from, to);
        this.gameSession = gameSession;
    }

    public void exec(GameMechanics gameMechanics) {
        gameSession.start();
        gameMechanics.getMessageSystem().sendMessage(new MessageGameStarted(getTo(), getFrom(), gameSession));

        // TODO: сообщение на фронтенд: gameStarted!

    }
}
