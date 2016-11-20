package dao;

import dataSets.UserDataSet;
import dbexecutor.TExecutor;
import dbexecutor.TResultHandler;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.SessionImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersDAO {
    private Connection connection;
    private SessionFactory sessionFactory;

    public UsersDAO(Connection connection) {
        this.connection = connection;
    }

    public UsersDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public UserDataSet get(long id) throws SQLException {

        return TExecutor.execQuery(connection, "SELECT * FROM users WHERE id=" + id, new TResultHandler<UserDataSet>() {

            public UserDataSet handle(ResultSet result) throws SQLException {
                result.next();
                return new UserDataSet(result.getLong(1), result.getString(2));
            }

        });
    }

    public UserDataSet get(String name) throws SQLException {


//        return new UserDataSet(1, name);
        return TExecutor.execQuery(connection, "SELECT * FROM users WHERE name='" + name + "'", new TResultHandler<UserDataSet>() {
            public UserDataSet handle(ResultSet result) throws SQLException {
//                while (result.next()){
//                    System.out.println("result.getLong(1): " + result.getLong(1));
//                }
                result.next();
                System.out.println("result.getLong(1): " + result.getLong("id"));
                System.out.println("result.getString(2): " + result.getString("name"));
                return new UserDataSet(result.getLong("id"), result.getString("name"));
            }
        });
    }

    public void save(UserDataSet dataSet) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(dataSet);
        transaction.commit();
        session.close();
    }

    public UserDataSet read(long id) {
        Session session = sessionFactory.openSession();
        return (UserDataSet) session.load(UserDataSet.class, id);
    }

    public UserDataSet read(String name) {
        SessionImpl session = (SessionImpl) sessionFactory.openSession();
        List userDataSets = session.createCriteria(UserDataSet.class)
                .add(Restrictions.like("name", name))
                .setMaxResults(1)
                .list();
        return (UserDataSet) userDataSets.get(0);
    }
}