package main;

import accountService.AccountServiceImpl;
import frontend.FrontendImpl;
import gameMechanics.GameMechanicsImpl;
import messageSystem.MessageSystemImpl;
import org.eclipse.jetty.server.Server;
import org.maltparser.concurrent.ConcurrentMaltParserModel;
import org.maltparser.concurrent.ConcurrentMaltParserService;
import org.maltparser.core.exception.MaltChainedException;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {

    public static void main(String[] args) throws Exception {
        MessageSystemImpl messageSystem = new MessageSystemImpl();

        FrontendImpl frontend = new FrontendImpl(messageSystem);
        AccountServiceImpl accountService = new AccountServiceImpl(messageSystem);
        GameMechanicsImpl gameMechanics = new GameMechanicsImpl(messageSystem);

        (new Thread(frontend)).start();
        (new Thread(accountService)).start();
        (new Thread(gameMechanics)).start();

        Server server = new Server(8080);
        server.setHandler(frontend);
        server.start();
        server.join();
    }

}