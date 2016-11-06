package gameMechanics;

import base.Address;
import base.GameMechanics;
import base.MessageSystem;
import utils.ThreadSleepHelper;

import java.util.HashMap;
import java.util.Map;

public class GameMechanicsImpl implements GameMechanics, Runnable {

    private final Map<Integer, GameSession> userToSessions = new HashMap<Integer, GameSession>();
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
            processMessages();
            doGMStep();
            replicateGamesToFrontend();
            ThreadSleepHelper.sleep(ThreadSleepHelper.SHORT_SLEEP_TIME);
        }
    }

    private void replicateGamesToFrontend() {
        // todo: отправка на фронтенд изменений User Session
    }

    private void doGMStep() {
        // todo: расчет изменений, не связанных с текущими командами пользователей
    }

    private void processMessages() {
        messageSystem.execForAbonent(this);
    }

    public MessageSystem getMessageSystem() {
        return messageSystem;
    }
}
