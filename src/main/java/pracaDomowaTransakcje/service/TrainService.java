package pracaDomowaTransakcje.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TrainService {

    private Connection connection;

    public TrainService(Connection connection) {
        this.connection = connection;
    }

    public void transactionTrain() throws SQLException {

        try{
            connection.setAutoCommit(false);

            // DELETE
            PreparedStatement prepStm1 = null;

            // TO POD SPODEM TO TRANSAKCJA
            try {
                prepStm1 = connection.prepareStatement("delete from train where name = ?");
                prepStm1.setString(1, "Przemek");

                int deletedRows = prepStm1.executeUpdate();
                System.out.println("Usunieto: " + deletedRows);
            } finally {
                if (prepStm1 != null) prepStm1.close();
            }

            // INSERT
            PreparedStatement prepStm2 = null;
            try {
                prepStm2 = connection.prepareStatement("insert into train (name, length, isWars, seatNumber) values (?, ?, ?, ?);");
                prepStm2.setString(1, "Justyna");
                prepStm2.setDouble(2, 2000.0);
                prepStm2.setBoolean(3, true);
                prepStm2.setInt(4, 200);


                int insertedRows = prepStm2.executeUpdate();
                System.out.println("Dodano: " + insertedRows);
            } finally {
                if (prepStm2 != null) prepStm2.close();
            }

            connection.commit();

        } catch (SQLException e) {
            System.out.println("ROLLBACK!!!!!");
            connection.rollback();
            e.printStackTrace();
        } finally {
            if (connection != null) connection.close();
        }
    }


}
