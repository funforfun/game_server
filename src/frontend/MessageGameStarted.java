package frontend;

import base.Address;
import base.Frontend;
import gameMechanics.GameSession;
import messageSystem.MessageToFrontend;

public class MessageGameStarted extends MessageToFrontend {
    private GameSession gameSession;

    public MessageGameStarted(Address from, Address to, GameSession gameSession) {
        super(from, to);
        this.gameSession = gameSession;
    }

    @Override
    public void exec(Frontend frontend) {
        frontend.addRunningGameSessions(gameSession);
    }
}
