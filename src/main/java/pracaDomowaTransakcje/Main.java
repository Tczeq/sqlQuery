package pracaDomowaTransakcje;

import pracaDomowaTransakcje.train.connection.Config;
import pracaDomowaTransakcje.train.model.Train;
import pracaDomowaTransakcje.train.service.TrainService;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        Config config = new Config();

        try (Connection connection = config.getConnection()) {
            TrainService service = new TrainService(connection);

            //DELETE
            String deleteName = "Karolina";
            //INSERT
            Train newTrain = new Train("Janusz", 213.02, true, 2132);
            //UPDATE
            String oldName = "Justa";
            String newName = "Justyna";

            service.delete(deleteName);

            service.transactionTrain(deleteName, newTrain, oldName, newName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
