package pracaDomowaTransakcje;

import pracaDomowaTransakcje.connection.Config;
import pracaDomowaTransakcje.service.TrainService;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        Config config = new Config();

        try (Connection connection = config.getConnection()) {
            TrainService service = new TrainService(connection);
            service.transactionTrain();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
