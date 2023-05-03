package by.tms.insta.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionJdbc {
    private static ConnectionJdbc instance;

    private ConnectionJdbc() {
    }

    public static ConnectionJdbc getInstance() {
        if (instance == null){
            instance = new ConnectionJdbc();
        }
        return instance;
    }

    public Connection getPostgresConnection(){
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
