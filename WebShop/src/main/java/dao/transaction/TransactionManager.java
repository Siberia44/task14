package dao.transaction;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManager {

    private static final String CONNECTION_WAS_NOT_CLOSED = "Connection wasn't closed";
    private static final String CHANGES_NOT_SAVED = "Changes wasn't save";
    private static final String CHANGES_NOT_BE_ROLLBACK = "Changes wasn't be rollback";
    private static final String CONNECTION_NOT_CREATED = "Connection wasn't created";
    private BasicDataSource dataSource;

    public TransactionManager(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public <T> T doInTransaction(TransactionOperation<T> operation) {
        T result = null;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            result = operation.produce(connection);
            commitChanges(connection);
        } catch (SQLException e) {
            rollbackChanges(connection);
        }
        closeConnection(connection);
        return result;
    }

    private void closeConnection(Connection connection) {
        if (!isConnectionNull(connection)) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println(CONNECTION_WAS_NOT_CLOSED);
            }
        }
    }

    private void commitChanges(Connection connection) {
        if (!isConnectionNull(connection)) {
            try {
                connection.commit();
            } catch (SQLException e) {
                System.err.println(CHANGES_NOT_SAVED);
            }
        }
    }

    private void rollbackChanges(Connection connection) {
        if (!isConnectionNull(connection)) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                System.err.println(CHANGES_NOT_BE_ROLLBACK);
            }
        }
    }

    private boolean isConnectionNull(Connection connection) {
        if (connection == null) {
            System.err.println(CONNECTION_NOT_CREATED);
            return true;
        }
        return false;
    }
}
