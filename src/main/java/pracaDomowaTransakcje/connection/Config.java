package pracaDomowaTransakcje.connection;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class Config {

    private BasicDataSource dataSource;

    public Config() {
        dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/pociagi?useSSL=false&serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
