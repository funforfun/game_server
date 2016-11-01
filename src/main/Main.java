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
//        URL maltModelURL = new File("rus-test.mco").toURI().toURL();
//
//        ConcurrentMaltParserModel maltParserModel = ConcurrentMaltParserService.initializeParserModel(maltModelURL);
//
//        String[] sentences = {"Папа пошел лес."};
//
//        String[] output = maltParserModel.parseTokens(sentences);
//        int i = 0;

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

    public static void maltParserTest() {
        File file = new File("rus-test.mco");
//        URI uri = file.toURI();
        try {
            ConcurrentMaltParserModel maltParserModel = ConcurrentMaltParserService.initializeParserModel(file);

//            List<String[]> sentences = new ArrayList<String[]>();

            String[] sentences = {"Папа пошел лес."};

            String[] output = maltParserModel.parseTokens(sentences);
            int i = 0;
//            MaltParserRunnable maltParserRunnable = new MaltParserRunnable(sentences, maltParserModel);
//            maltParserRunnable.run();

        } catch (MaltChainedException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }
}