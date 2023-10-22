import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {


        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ksiegarnia?useSSL=false&serverTimezone=UTC",
                "root",
                "root");


        Statement statement = connection.createStatement();

        // SELECT
        String select = "select * from klient";

        ResultSet resultSet = statement.executeQuery(select);

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





    }
}
