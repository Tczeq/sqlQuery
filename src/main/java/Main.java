import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {


//        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ksiegarnia?useSSL=false&serverTimezone=UTC",
//                "root",
//                "root");
//
//
//        Statement statement = connection.createStatement();
//
//        // SELECT
//        String select = "select * from klient";
//
//        ResultSet resultSet = statement.executeQuery(select);

//        while (resultSet.next()) {
//            int id = resultSet.getInt(1);
//            String name = resultSet.getString(2);
//            System.out.println(id + " " + name);
//        }

        // insert
//        String insert = "insert into klient values(null, 'Lukasz','Raczka')";
//        int insertRows = statement.executeUpdate(insert);
//        System.out.println("Dodano: " + insertRows);


        // update
//        String update = "update klient set nazwisko = 'Nozka' where idklienta = 14";
//        int updatedRows = statement.executeUpdate(update);
//        System.out.println("Zaktualizowano: " + updatedRows);



        // Delete
//        String delete = "delete from klient where idklienta = 5";
//        int deletedRows = statement.executeUpdate(delete);
//        System.out.println("Usunieto: " + deletedRows);


        // METODY NIE POWINNE BYC STATYCZNE













        /*
        * Tego uzywamy do projektow czyli to co nas zabezpieczy przed sql injection
        * */
//        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ksiegarnia?useSSL=false&serverTimezone=UTC",
//                "root",
//                "root");
//
//        String select1 = "select * from ksiazka where cena > ? and gatunek = ?";
//
//        PreparedStatement preparedStatement = connection.prepareStatement(select1);
//
//        preparedStatement.setInt(1, 80);
//        preparedStatement.setString(2, "kryminal");
//
//        ResultSet resultSet1 = preparedStatement.executeQuery();
//        while (resultSet1.next()) {
//            System.out.println(resultSet1.getString(2));
//        }


//        BasicDataSource dataSource = new BasicDataSource();
//        dataSource.setUrl("jdbc:mysql://localhost:3306/ksiegarnia?useSSL=false&serverTimezone=UTC");
//        dataSource.setUsername("root");
//        dataSource.setPassword("root");
//
//        Connection connection = null;
//        Statement statement = null;
//        ResultSet resultSet = null;
//
//
//        try {
//            connection = dataSource.getConnection();
//            statement = connection.createStatement();
//            resultSet = statement.executeQuery("select * from ksiazka");
//
//            while (resultSet.next()) {
//                System.out.println(resultSet.getString(2));
//
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//
//            // RECZNE ZAMYKANIE
//            // I TO WAZNE -> W ODWROTNEJ KOLEJNOSCI NIZ JE OTWIERALISMY
//        } finally {
//            if(resultSet != null) resultSet.close();
//            if(statement != null) statement.close();
//            if(connection != null) connection.close();
//        }





        // a teraz to samo tylko przy uzyciu try-with-resources
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/ksiegarnia?useSSL=false&serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("root");


        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from ksiazka")) {

            while (resultSet.next()) {
                System.out.println(resultSet.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }





    }
}
