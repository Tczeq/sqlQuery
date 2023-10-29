package pracaDomowaTransakcje.employeeManagement;



import pracaDomowaTransakcje.employeeManagement.connection.Config;
import pracaDomowaTransakcje.employeeManagement.model.JobPosition;
import pracaDomowaTransakcje.employeeManagement.model.Employee;
import pracaDomowaTransakcje.employeeManagement.service.EmployeeService;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        Config config = new Config();

        try (Connection connection = config.getConnection()) {
            EmployeeService service = new EmployeeService(connection);

            Employee p1 = new Employee("Janusz", "Nowowski", JobPosition.stazysta, 20000, true);
            Employee p2 = new Employee("Wlodek", "Karibczyk", JobPosition.mid_dev, 15000, true);

//            service.hireEmployee(p1);



//            service.hireEmployee(p2);

//            service.fireEmployee(3);

//            service.decreaseSalary(6, 180000);

//            service.promotedPosition(6, JobPosition.junior_dev);



            service.findEmployee("Janusz","Nowowski");


//            service.hireEmployee(p1);
//            service.hireEmployee(p2);

//            service.fireEmployee(10);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
