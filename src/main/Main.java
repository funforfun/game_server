package main;

import accountService.DatabaseServiceImpl;
import dbexecutor.TResultHandler;
import frontend.FrontendImpl;
import gameMechanics.GameMechanicsImpl;
import messageSystem.MessageSystemImpl;
import org.eclipse.jetty.server.Server;
import resource.Resource;
import resource.ResourceFactory;
import resource.ResourcesMap;
import dbexecutor.TExecutor;
import utils.TimeService;
import vfs.VFSImpl;

import java.sql.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
//        jdbcTest();
        // timer + resources + vfs
//        timerTest();


        MessageSystemImpl messageSystem = new MessageSystemImpl();

        FrontendImpl frontend = new FrontendImpl(messageSystem);
        DatabaseServiceImpl accountService = new DatabaseServiceImpl(messageSystem);
        GameMechanicsImpl gameMechanics = new GameMechanicsImpl(messageSystem);

        (new Thread(frontend)).start();
        (new Thread(accountService)).start();
        (new Thread(gameMechanics)).start();

        Server server = new Server(8080);
        server.setHandler(frontend);
        server.start();
        server.join();
    }

    private static void jdbcTest() {
        try {
            Driver driver = new com.mysql.jdbc.Driver();
            System.out.println(driver.getMajorVersion() + "." + driver.getMinorVersion());
            DriverManager.registerDriver(driver);
            StringBuilder url = new StringBuilder();
            url.
                    append("jdbc:mysql://"). // db type
                    append("localhost:"). // host name
                    append("3306/"). // port
                    append("test_db?"). // db name
                    append("user=test_user&"). // login
                    append("password=1234"); // password
            System.out.println(url.toString());
            // TODO: сделать connection pool (чисто для себя)
            Connection connection = DriverManager.getConnection(url.toString());

            // 1
            String[] strings = new String[20];
            strings[0] = "DROP TABLE users";
            strings[1] = "CREATE TABLE users (id bigint auto_increment, name varchar(256), primary key (id))";
            TExecutor.execUpdate(connection, strings);

            // 2
            Map<Integer, String> map = new HashMap<Integer, String>();
            map.put(1, "Vasya");
            map.put(2, "Petya");
            TExecutor.execUpdate(connection, map);

            // 3
//            String query = "SELECT * FROM users";
            String query = "SELECT name FROM users";
            String name = TExecutor.execQuery(connection, query, new TResultHandler<String>() {
                public String handle(ResultSet result) throws SQLException {
                    result.next();
                    return result.getString("name");
                }
            });
            System.out.println(name);

//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            resultSet.next();

//            System.out.println(resultSet.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }


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
                Resource resource = ResourceFactory.getInstance().getResource(path);
                ResourcesMap.put(resource);
            }
        }
        Map map = ResourcesMap.getResourcesMap();
        int x = 1;
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