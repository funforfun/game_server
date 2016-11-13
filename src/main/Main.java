package main;

import accountService.AccountServiceImpl;
import frontend.FrontendImpl;
import gameMechanics.GameMechanicsImpl;
import messageSystem.MessageSystemImpl;
import org.eclipse.jetty.server.Server;
import org.maltparser.concurrent.ConcurrentMaltParserModel;
import org.maltparser.concurrent.ConcurrentMaltParserService;
import org.maltparser.core.exception.MaltChainedException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import sax.ReaderXMLFile;
import utils.TimeService;
import vfs.VFSImpl;

import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.TimerTask;

public class Main {

    public static void main(String[] args) throws Exception {
        timerTest();
//        vfsTest();


//        MessageSystemImpl messageSystem = new MessageSystemImpl();
//
//        FrontendImpl frontend = new FrontendImpl(messageSystem);
//        AccountServiceImpl accountService = new AccountServiceImpl(messageSystem);
//        GameMechanicsImpl gameMechanics = new GameMechanicsImpl(messageSystem);
//
//        (new Thread(frontend)).start();
//        (new Thread(accountService)).start();
//        (new Thread(gameMechanics)).start();
//
//        Server server = new Server(8080);
//        server.setHandler(frontend);
//        server.start();
//        server.join();
    }

    private static void vfsTest() {
        VFSImpl vfs = new VFSImpl("");
        System.out.println("Absolute path to here: " + vfs.getAbsolutePath(""));
        Iterator<String> iterator = vfs.getIterator("./data");
        String path;
        while (iterator.hasNext()) {
            path = iterator.next();
            if (!vfs.isDirectory(path)) {
                System.out.println(path);
                Object object = ReaderXMLFile.readXML(path);
                int x = 1;

//                // вариант 2, через DOM:
//                File xmlFileDom = new File(path);
//                DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
//                DocumentBuilder documentBuilder = null;
//                try {
//                    documentBuilder = documentBuilderFactory.newDocumentBuilder();
//                    org.w3c.dom.Document doc = documentBuilder.parse(xmlFileDom);
//                    NodeList nodes = doc.getElementsByTagName("class");
//                    int x2 = 1;
//                } catch (ParserConfigurationException e) {
//                    e.printStackTrace();
//                } catch (SAXException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }

            }
        }
    }

    private static void timerTest() {
        TimeService.getInstance().start();
        TimeService.getInstance().scheduleTask(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Hello from timer!");
                TimeService.getInstance().stop();
                Main.vfsTest();
            }
        }, 2000);
    }

}