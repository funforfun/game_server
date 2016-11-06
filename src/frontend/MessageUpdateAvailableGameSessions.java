package frontend;

import base.Address;
import base.Frontend;
import gameMechanics.GameSession;
import messageSystem.MessageToFrontend;

public class MessageUpdateAvailableGameSessions extends MessageToFrontend {
    private GameSession gameSession;

    public MessageUpdateAvailableGameSessions(Address from, Address to, GameSession gameSession) {
        super(from, to);

        this.gameSession = gameSession;
    }

    @Override
    public void exec(Frontend frontend) {
        frontend.addWaitingGameSession(gameSession);
    }
}
