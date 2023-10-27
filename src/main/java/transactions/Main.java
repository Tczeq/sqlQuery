package transactions;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {


//   Zasady ACID (anagram) to zbiór zasad którę opisuja transakcje
//   Atomowość - wykonanie wszystkich kroków wchodzqcych w skład transakcll decydule o jej sukcesie;
//   Spojnosc - stan bagy danych zawsze przedstawiaja stan przed lub po transakcji;
//   Izolacja - transakcja jest odizolowana od innych transakcji, dziala niezaleznie od pozostalych;
//   Trwalosc - w przypadku awarii systemu bazodanowego, transakcja albo wykonana jest w calosci albo wcale;

    public static void main(String[] args) throws SQLException {


        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/ksiegarnia?useSSL=false&serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("root");



        Connection connection = null;

//        try {
//            connection = dataSource.getConnection();
//            connection.setAutoCommit(false);
//
//            //DELETE
//
//            PreparedStatement preparedStatement1 = null;
//
//            try {
//                preparedStatement1 = connection.prepareStatement("delete from klient where nazwisko = ?");
//                preparedStatement1.setString(1, "Treczko");
//
//                int deleteRows = preparedStatement1.executeUpdate();
//                System.out.println("usunieto: " + deleteRows);
//
//            } finally {
//                if(preparedStatement1 != null) preparedStatement1.close();
//            }
//
//            //INSERT
//            PreparedStatement preparedStatement2 = null;
//            try {
//                preparedStatement2 = connection.prepareStatement("insert into klient values(null, ?, ?);");
//                preparedStatement2.setString(1, "Lukasz");
//                preparedStatement2.setString(2, "dupa");
//
//                int insertRows = preparedStatement2.executeUpdate();
//                System.out.println("Dodano: " + insertRows);
//            } finally {
//                if(preparedStatement2 != null) preparedStatement2.close();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            if(connection != null) connection.close();
//        }

        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            // DELETE
            PreparedStatement prepStm1 = null;

            // TO POD SPODEM TO TRANSAKCJA
            try {
                prepStm1 = connection.prepareStatement("delete from klient where nazwisko = ?");
                prepStm1.setString(1, "dupa");

                int deletedRows = prepStm1.executeUpdate();
                System.out.println("Usunieto: " + deletedRows);
            } finally {
                if (prepStm1 != null) prepStm1.close();
            }

            // INSERT
            PreparedStatement prepStm2 = null;
            try {
                prepStm2 = connection.prepareStatement("insert into klient (imie, nazwisko, miasto) values (?, ?, ?);");
                prepStm2.setString(1, "Justyna");
                prepStm2.setString(2, "Koniczynka");
                prepStm2.setString(3, "Wegorzewo");


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
