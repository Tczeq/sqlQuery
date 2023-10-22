package pracaDomowa;

import pracaDomowa.model.Car;
import pracaDomowa.service.SQLService;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {

        SQLService service = new SQLService();
        Car car1 = new Car(1, "Audi", "A5", 1500);
        Car car2 = new Car(3, "Audi", "A6", 15000);
        Car car3 = new Car(4, "BMW", "M3", 1000);
        Car car3Update = new Car(4, "BMW", "M4", 10020);

//        SQLService.insertToDB(car1);

//        SQLService.deleteFromDB(1);

//        SQLService.updateToDB(car2);

//        SQLService.insertToDB(car1);
//        SQLService.deleteFromDB(2);
//        SQLService.updateToDB(car3Update);
    }

}
