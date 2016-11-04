package gameMechanics;

import base.Abonent;
import base.Address;
import base.GameMechanics;
import base.Message;

public abstract class MessageToGameMechanics extends Message {
    public MessageToGameMechanics(Address from, Address to) {
        super(from, to);
    }

    public void exec(Abonent gameMechanics) {
        if (gameMechanics instanceof GameMechanics) {
            exec((GameMechanics) gameMechanics);
        } else {
            throw new RuntimeException("Wrong type of abonent!" + gameMechanics.getClass());
        }
    }

    public abstract void exec(GameMechanics gameMechanics);
}
