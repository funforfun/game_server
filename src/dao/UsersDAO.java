package dao;

import dataSets.UsersDataSet;
import dbexecutor.TExecutor;
import dbexecutor.TResultHandler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersDAO {
    private Connection connection;

    public UsersDAO(Connection connection) {
        this.connection = connection;
    }

    public UsersDataSet get(long id) throws SQLException {

        return TExecutor.execQuery(connection, "SELECT * FROM users WHERE id=" + id, new TResultHandler<UsersDataSet>() {

            public UsersDataSet handle(ResultSet result) throws SQLException {
                result.next();
                return new UsersDataSet(result.getLong(1), result.getString(2));
            }

        });
    }

    public UsersDataSet get(String name) throws SQLException {

        return TExecutor.execQuery(connection, "SELECT * FROM users WHERE name=" + name, new TResultHandler<UsersDataSet>() {

            public UsersDataSet handle(ResultSet result) throws SQLException {
                result.next();
                return new UsersDataSet(result.getLong(1), result.getString(2));
            }

        });
    }
}