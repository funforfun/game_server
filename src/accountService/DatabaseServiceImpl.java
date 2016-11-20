package accountService;

import base.DatabaseService;
import base.Address;
import base.MessageSystem;
import dao.UsersDAO;
import dataSets.UserDataSet;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import utils.ThreadSleepHelper;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseServiceImpl implements DatabaseService, Runnable {
    private Address address;
    private final MessageSystem messageSystem;
    private UsersDAO usersDAO;

//    public DatabaseServiceImpl(MessageSystem messageSystem) {
//        this.messageSystem = messageSystem;
//        this.address = new Address();
//        messageSystem.addService(this);
//
//        try {
//            Driver driver = new com.mysql.jdbc.Driver();
//            System.out.println(driver.getMajorVersion() + "." + driver.getMinorVersion());
//            DriverManager.registerDriver(driver);
//            StringBuilder url = new StringBuilder();
//            url.
//                    append("jdbc:mysql://"). // db type
//                    append("localhost:"). // host name
//                    append("3306/"). // port
//                    append("test_db?"). // db name
//                    append("user=test_user&"). // login
//                    append("password=1234"); // password
//
//            System.out.println(url.toString());
//
//            Connection connection = DriverManager.getConnection(url.toString());
//            usersDAO = new UsersDAO(connection);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public DatabaseServiceImpl(MessageSystem messageSystem) {
        this.messageSystem = messageSystem;
        this.address = new Address();
        messageSystem.addService(this);

        Configuration configuration = new Configuration();
        // Добавляем сохраняемый объект в конфиг
        configuration.addAnnotatedClass(UserDataSet.class);
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/test_db");
        configuration.setProperty("hibernate.connection.username", "test_user");
        configuration.setProperty("hibernate.connection.password", "1234");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "update");


        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        StandardServiceRegistry serviceRegistry = builder.build();
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        usersDAO = new UsersDAO(sessionFactory);
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
            UserDataSet userDataSet = usersDAO.get(name);
            user_id = userDataSet.getId();
        } catch (SQLException e) {
//            e.printStackTrace();
            System.out.println("Unknown player! " + name);
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
