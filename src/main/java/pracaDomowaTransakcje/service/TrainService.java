package pracaDomowaTransakcje.service;

import pracaDomowaTransakcje.exception.RollBackException;
import pracaDomowaTransakcje.model.Train;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TrainService {

    private Connection connection;

    public TrainService(Connection connection) {
        this.connection = connection;
    }

    public void delete(String name) throws SQLException {

        try (PreparedStatement prepStm1 = connection.prepareStatement("delete from train where name = ?")) {
            prepStm1.setString(1, name);

            int deletedRows = prepStm1.executeUpdate();
            System.out.println("Usunieto: " + deletedRows);
            if (deletedRows == 0) {
                throw new RollBackException("Operacja DELETE nie powiodla sie. Rollback");
            }
        }
    }

    public void insert(Train train) throws SQLException {
        try (PreparedStatement prepStm2 = connection.prepareStatement("insert into train (name, length, isWars, seatNumber) values (?, ?, ?, ?);")) {
            prepStm2.setString(1, train.getName());
            prepStm2.setDouble(2, train.getLength());
            prepStm2.setBoolean(3, train.isWars());
            prepStm2.setInt(4, train.getSeatNumber());

            int insertedRows = prepStm2.executeUpdate();
            System.out.println("Dodano: " + insertedRows);
        }
    }

    public void update(String actuallName, String newName) throws SQLException {
        try (PreparedStatement prepStm3 = connection.prepareStatement("UPDATE train SET name = ? WHERE name = ?")){

            // Nowa imie
            prepStm3.setString(1, newName);
            //Istniejace imie
            prepStm3.setString(2, actuallName);

            int updatedRows = prepStm3.executeUpdate();
            System.out.println("Zaktualizowano: " + updatedRows);
            if (updatedRows == 0) {
                throw new RollBackException("Operacja DELETE nie powiodla sie. Rollback");
            }
        }
    }

    public void transactionTrain(String deleteName, Train insertTrain, String oldName, String newName) throws SQLException {

        try {
            connection.setAutoCommit(false);

            delete(deleteName);

            insert(insertTrain);

            update(oldName, newName);

            connection.commit();

        } catch (RollBackException e) {
            System.out.println("ROLLBACK!!!!!");
            connection.rollback();
            e.printStackTrace();
        } finally {
            if (connection != null) connection.close();
        }
    }


}
