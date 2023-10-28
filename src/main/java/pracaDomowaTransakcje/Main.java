package pracaDomowaTransakcje;

import pracaDomowaTransakcje.connection.Config;
import pracaDomowaTransakcje.model.Train;
import pracaDomowaTransakcje.service.TrainService;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        Config config = new Config();

        try (Connection connection = config.getConnection()) {
            TrainService service = new TrainService(connection);

            //DELETE
            String deleteName = "Martyna";
            //INSERT
            Train newTrain = new Train("Janusz", 213.02, true, 2132);
            //UPDATE
            String oldName = "Justa";
            String newName = "Justyna";


            service.transactionTrain(deleteName, newTrain, oldName, newName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
