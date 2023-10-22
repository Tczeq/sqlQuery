package pracaDomowa.service;

import pracaDomowa.connection.Config;
import pracaDomowa.model.Car;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLService {


    public static void insertToDB(Car car) throws SQLException {
//        Connection connection = Config.getConnection();


        String insertQuery = "insert into car values('" +
                car.getId() + "', '" +
                car.getBrand() + "', '" +
                car.getModel() + "', " +
                car.getMileage() + ")";

//        Statement statement = connection.createStatement();
        int insertRows = updateQuery(insertQuery);
        System.out.println("Dodano: " + insertRows);
    }

    public static void deleteFromDB(int idCar) throws SQLException {
//        Connection connection = Config.getConnection();


        String deleteQuery = "delete from car where id = " + idCar;

//        Statement statement = connection.createStatement();
        int insertRows = updateQuery(deleteQuery);
        System.out.println("Usunieto: " + insertRows);
    }

    public static void updateToDB(Car car) throws SQLException {
//        Connection connection = Config.getConnection();


        String updateQuery = "UPDATE car SET brand = '" + car.getBrand() +
                "', model = '" + car.getModel() +
                "', mileage = " + car.getMileage() +
                " WHERE id = " + car.getId();

//        Statement statement = connection.createStatement();
        int insertRows = updateQuery(updateQuery);
        System.out.println("Zaktualizowano: " + insertRows);
    }
    private static int updateQuery(String query) throws SQLException {
        Connection connection = Config.getConnection();
        Statement statement = connection.createStatement();
        return statement.executeUpdate(query);
    }
}
