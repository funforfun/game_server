package gameMechanics;

import base.Address;
import base.GameMechanics;

public class MsgStartGameSession extends MessageToGameMechanics {
    public MsgStartGameSession(Address from, Address to) {
        super(from, to);
    }

    public void exec(GameMechanics gameMechanics) {
    }
}
