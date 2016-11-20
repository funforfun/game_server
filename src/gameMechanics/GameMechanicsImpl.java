package gameMechanics;

import base.Address;
import base.GameMechanics;
import base.MessageSystem;
import frontend.FrontendImpl;
import frontend.MessageGameFinished;
import frontend.UserSession;
import utils.ThreadSleepHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameMechanicsImpl implements GameMechanics, Runnable {

    private final Map<Long, GameSession> userToGameSession = new HashMap<Long, GameSession>();
    private List<GameSession> gameSessions = new ArrayList<GameSession>();
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
            ThreadSleepHelper.sleep();
        }
    }

    private void replicateGamesToFrontend() {
        List<GameSession> removing = new ArrayList<GameSession>();
        for (GameSession gameSession : gameSessions) {
            if (gameSession.isFinished()) {
                System.out.println("Finished Game Session!");
                Address frontend = messageSystem.getAddressService().getAddress(FrontendImpl.class);
                messageSystem.sendMessage(new MessageGameFinished(getAddress(), frontend, gameSession));
                removing.add(gameSession);
            }
        }

        for (GameSession gameSession : removing) {
            gameSessions.remove(gameSession);
        }
    }

    private void doGMStep() {
        // todo: расчет изменений, не связанных с текущими командами пользователей
        for (GameSession gameSession : gameSessions) {
            if (gameSession.isStarted()) {
                System.out.println("Started Game Session!");
                gameSession.setWinner(gameSession.getFirstPlayer());
            }
        }
    }

    private void processMessages() {
        messageSystem.execForAbonent(this);
    }

    public void addUserToGameSession(UserSession userSession, GameSession gameSession) {
        userToGameSession.put(userSession.getUserId(), gameSession);
    }

    public GameSession createGameSession(UserSession userSession) {
        GameSession gameSession = new GameSession(this);
        gameSession.addPlayer(userSession);
        return gameSession;

    }

    @Override
    public void addGameSession(GameSession gameSession) {
        gameSessions.add(gameSession);
    }

    public MessageSystem getMessageSystem() {
        return messageSystem;
    }
}
