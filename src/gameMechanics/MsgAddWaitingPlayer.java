package gameMechanics;

import base.Address;
import base.GameMechanics;
import frontend.MessageUpdateAvailableGameSessions;
import frontend.MessageUpdateUserId;
import frontend.UserSession;
import messageSystem.MessageToGameMechanics;

public class MsgAddWaitingPlayer extends MessageToGameMechanics {
    private UserSession userSession;

    public MsgAddWaitingPlayer(Address from, Address to, UserSession userSession) {
        super(from, to);
        this.userSession = userSession;
    }

    @Override
    public void exec(GameMechanics gameMechanics) {
        GameSession gameSession = gameMechanics.createGameSession(userSession);

        gameMechanics.getMessageSystem().sendMessage(new MessageUpdateAvailableGameSessions(getTo(), getFrom(), gameSession));
    }
}
