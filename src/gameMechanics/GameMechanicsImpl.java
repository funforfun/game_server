package gameMechanics;

import base.Address;
import base.GameMechanics;
import base.MessageSystem;
import utils.ThreadSleepHelper;

public class GameMechanicsImpl implements GameMechanics, Runnable {

    private final MessageSystem messageSystem;
    private final Address address;

    public GameMechanicsImpl(MessageSystem messageSystem) {
        this.messageSystem = messageSystem;
        this.address = new Address();
        messageSystem.addService(this);
    }

    @Override
    public Address getAddress() {
        return address;
    }

    @Override
    public void run() {
        while (true) {
            messageSystem.execForAbonent(this);
            ThreadSleepHelper.sleep(10);
        }
    }

    public MessageSystem getMessageSystem() {
        return messageSystem;
    }
}
