package frontend;

import base.Address;
import base.Frontend;
import base.Message;
import gameMechanics.GameSession;
import messageSystem.MessageToFrontend;

public class MessageGameFinished extends MessageToFrontend {
    private GameSession gameSession;

    public MessageGameFinished(Address from, Address to, GameSession gameSession) {
        super(from, to);
        this.gameSession = gameSession;
    }

    @Override
    public void exec(Frontend frontend) {
        frontend.addFinishedGameSession(gameSession);
    }
}
