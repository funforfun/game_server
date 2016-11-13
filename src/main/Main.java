package main;

import resource.Resource;
import resource.ResourceFactory;
import utils.TimeService;
import vfs.VFSImpl;

import java.util.Iterator;
import java.util.TimerTask;

public class Main {

    public static void main(String[] args) throws Exception {
        timerTest();
//        initResources();


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

    private static void initResources() {
        VFSImpl vfs = new VFSImpl("");
        System.out.println("Absolute path to here: " + vfs.getAbsolutePath(""));
        Iterator<String> iterator = vfs.getIterator("./data");
        String path;
        while (iterator.hasNext()) {
            path = iterator.next();
            if (!vfs.isDirectory(path)) {
                System.out.println(path);
                ResourceFactory resourceFactory = ResourceFactory.getInstance();
                Resource resource = resourceFactory.getResource(path);
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
                Main.initResources();
            }
        }, 2000);
    }

}