package frontend;

import accountService.AccountServiceImpl;
import base.Address;
import base.Frontend;
import base.MessageSystem;
import gameMechanics.GameMechanicsImpl;
import gameMechanics.GameSession;
import gameMechanics.MsgAddWaitingPlayer;
import gameMechanics.MsgStartGameSession;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import utils.ThreadSleepHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class FrontendImpl extends AbstractHandler implements Frontend, Runnable {

    private static AtomicInteger handleCount = new AtomicInteger(0);
    private static AtomicInteger lastUserId = new AtomicInteger(1);
    private static Logger log = Logger.getLogger("TestLogName");
    private final MessageSystem messageSystem;

    private Address address;
    private Map<String, Integer> nameToId = new HashMap<String, Integer>();
    private Map<Integer, UserSession> sessionIdToSession = new HashMap<Integer, UserSession>();
    private Map<UserSession, GameSession> userSessionToGameSessions = new HashMap<UserSession, GameSession>();
    private List<GameSession> waitingGameSessions = new ArrayList<GameSession>();
    private List<GameSession> runningGameSessions = new ArrayList<GameSession>();
    private List<GameSession> finishedGameSessions = new ArrayList<GameSession>();


    public FrontendImpl(MessageSystem messageSystem) {
        this.messageSystem = messageSystem;
        this.address = new Address();
        messageSystem.addService(this);
    }


    @Override
    public void run() {
        while (true) {
            messageSystem.execForAbonent(this);
            removeDeadUsers();
            ThreadSleepHelper.sleep(10);
        }
    }

    private void removeDeadUsers() {
        // todo:
    }

    @Override
    public void handle(
            String s,
            Request request,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ) throws IOException, ServletException {
        handleCount.incrementAndGet();
        httpServletResponse.setContentType("text/html;charset=utf-8");
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        request.setHandled(true);

        if (!request.getMethod().equals("POST")) {
            httpServletResponse.getWriter().println(AuthenticationPageGenerator.getPageWithSessionId(lastUserId.getAndIncrement()));
            log.info(request.getMethod() + "! " + handleCount);
            return;
        }

        log.info("POST! " + handleCount);
        String name = request.getParameter("name");
        int session_id = Integer.parseInt(request.getParameter("session_id"));


        UserSession userSession = getUserSession(session_id, name);

        log.info("************************************************");
        log.info("Name from form: " + name);
        log.info("Session Id from form: " + session_id);
        log.info("Name from UserSession: " + userSession.getName());
        log.info("Session Id from UserSession: " + userSession.getSessionId());
        Integer id = nameToId.get(userSession.getName());
        log.info("ID: " + id);

        if (id != null) {
            userSession.setUserId(id);
            if (id == -1) {
                httpServletResponse.getWriter().println("<h1>User " + userSession.getName() + " is unknown!</h1>");
            } else {
                httpServletResponse.getWriter().println("<h1>User name: " + userSession.getName() + " Id:" + id + "</h1>");
            }
        } else {
            httpServletResponse.getWriter().println(AuthenticationPageGenerator.getPageWaitAuthorization(userSession.getName(), session_id));
            // TODO: AccountServiceImpl -> AccountService
            Address addressAccountService = messageSystem.getAddressService().getAddress(AccountServiceImpl.class);
            messageSystem.sendMessage(new MessageGetUserId(getAddress(), addressAccountService, userSession.getName()));
        }

        // TODO: не страртовать игровую механику, пока не было авторизации
        if (userSessionToGameSessions.containsKey(userSession)) {
            GameSession gameSession = userSessionToGameSessions.get(userSession);
            if (runningGameSessions.contains(gameSession)) {
                System.out.println("Игровая сессия уже идет!");
                return;
            }
        }

        // todo: game mechanic
        Address gameMechanics = messageSystem.getAddressService().getAddress(GameMechanicsImpl.class);
        if (waitingGameSessions.isEmpty()) {
            System.out.println("Создание игровой сессии игроком: " + userSession.getSessionId());
            messageSystem.sendMessage(new MsgAddWaitingPlayer(getAddress(), gameMechanics, userSession));
        } else {
            System.out.println("Есть ожидающие игроки!");
            if (!userSessionToGameSessions.containsKey(userSession)) {
                System.out.println("Подключение к игровой сессии игрока: " + userSession.getSessionId());
                GameSession gameSession = waitingGameSessions.get(0);
                userSessionToGameSessions.put(userSession, gameSession);
                messageSystem.sendMessage(new MsgStartGameSession(getAddress(), gameMechanics, gameSession));
            } else {
                System.out.println("Игрок " + userSession.getSessionId() + " уже ожидает другого игрока!");
            }
        }

    }

    public void setId(String name, Integer id) {
        nameToId.put(name, id);
    }

    public void addWaitingGameSession(GameSession gameSession) {
        System.out.println("addWaitingGameSession");
        waitingGameSessions.add(gameSession);
        userSessionToGameSessions.put(gameSession.getFirstPlayer(), gameSession);
    }

    @Override
    public void addRunningGameSessions(GameSession gameSession) {
        System.out.println("addRunningGameSessions");
        runningGameSessions.add(gameSession);
        waitingGameSessions.remove(gameSession);
    }

    @Override
    public void addFinishedGameSession(GameSession gameSession) {
        System.out.println("addFinishedGameSession");
        finishedGameSessions.add(gameSession);
        runningGameSessions.remove(gameSession);
    }


    private UserSession getUserSession(int session_id, String name) {
        if (sessionIdToSession.containsKey(session_id)) {
            return sessionIdToSession.get(session_id);
        }

        UserSession userSession = new UserSession();
        userSession.setName(name);
        sessionIdToSession.put(session_id, userSession);
        return userSession;
    }

    public Address getAddress() {
        return address;
    }
}