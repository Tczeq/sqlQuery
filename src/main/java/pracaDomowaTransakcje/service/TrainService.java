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

        try {
            connection.setAutoCommit(false);

            // DELETE
            PreparedStatement prepStm1 = null;

            try {
                prepStm1 = connection.prepareStatement("delete from train where name = ?");
                prepStm1.setString(1, "Martyna");

                int deletedRows = prepStm1.executeUpdate();
                System.out.println("Usunieto: " + deletedRows);
                if (deletedRows == 0) {
                    connection.rollback();
                    System.out.println("Operacja DELETE nie powiodla sie. Wykonuje rollback");
                    return;
                }
            } finally {
                if (prepStm1 != null) prepStm1.close();
            }

            // INSERT
            PreparedStatement prepStm2 = null;
            try {
                prepStm2 = connection.prepareStatement("insert into train (name, length, isWars, seatNumber) values (?, ?, ?, ?);");
                prepStm2.setString(1, "Janusz");
                prepStm2.setDouble(2, 213.02);
                prepStm2.setBoolean(3, true);
                prepStm2.setInt(4, 2132);

                int insertedRows = prepStm2.executeUpdate();
                System.out.println("Dodano: " + insertedRows);
            } finally {
                if (prepStm2 != null) prepStm2.close();
            }

            // UPDATE
            PreparedStatement prepStm3 = null;
            try {
                prepStm3 = connection.prepareStatement("UPDATE train SET name = ? WHERE name = ?");
                // Nowa imie
                prepStm3.setString(1, "Justyna");
                //Istniejace imie
                prepStm3.setString(2, "Justa");

                int updatedRows = prepStm3.executeUpdate();
                System.out.println("Zaktualizowano: " + updatedRows);
                if (updatedRows == 0) {
                    connection.rollback();
                    System.out.println("Operacja UPDATE nie powiodla sie. Wykonuje rollback");
                    return;
                }
            } finally {
                if (prepStm3 != null) prepStm3.close();
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
