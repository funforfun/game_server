package main;

import accountService.AccountServiceImpl;
import frontend.FrontendImpl;
import gameMechanics.GameMechanicsImpl;
import messageSystem.MessageSystemImpl;
import org.eclipse.jetty.server.Server;
import org.maltparser.concurrent.ConcurrentMaltParserModel;
import org.maltparser.concurrent.ConcurrentMaltParserService;
import org.maltparser.core.exception.MaltChainedException;
import utils.TimeService;
import vfs.VFSImpl;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.TimerTask;

public class Main {

    public static void main(String[] args) throws Exception {
        timerTest();
        vfsTest();


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

    private static void vfsTest() {
        VFSImpl vfs = new VFSImpl("");
        System.out.println("Absolute path to here: " + vfs.getAbsolutePath(""));
        Iterator<String> iterator = vfs.getIterator("./src");
        while (iterator.hasNext()) {
            System.out.println(iterator.next() + "\n");
        }
    }

    public static void timerTest() {
        TimeService.getInstance().start();
        TimeService.getInstance().scheduleTask(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Hello from timer!");
                TimeService.getInstance().stop();
            }
        }, 5000);
    }

}