package accountService;

import base.DatabaseService;
import base.Address;
import base.MessageSystem;
import dao.UsersDAO;
import dataSets.UsersDataSet;
import utils.ThreadSleepHelper;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DatabaseServiceImpl implements DatabaseService, Runnable {
    private Address address;
    private final MessageSystem messageSystem;
    private Map<String, Integer> fakeAccounter = new HashMap<String, Integer>();
    private UsersDAO usersDAO;

    public DatabaseServiceImpl(MessageSystem messageSystem) {
        this.messageSystem = messageSystem;
        this.address = new Address();
        messageSystem.addService(this);
        this.fakeAccounter.put("Tully", 1);
        this.fakeAccounter.put("Sully", 2);

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

            Connection connection = DriverManager.getConnection(url.toString());
            usersDAO = new UsersDAO(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        while (true) {
            messageSystem.execForAbonent(this);
            ThreadSleepHelper.sleep(10);
        }

    }

    public long getUserId(String name) {
        ThreadSleepHelper.sleep(5000);
        long user_id = -1;
        try {
            UsersDataSet usersDataSet = usersDAO.get(name);
            user_id = usersDataSet.getId();
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        Integer user_id = fakeAccounter.get(name);
//        return (user_id == -1) ? -1 : user_id;
        return user_id;
    }

    public MessageSystem getMessageSystem() {
        return messageSystem;
    }

    public Address getAddress() {
        return address;
    }
}
