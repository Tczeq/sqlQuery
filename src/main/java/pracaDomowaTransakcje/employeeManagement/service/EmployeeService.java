package pracaDomowaTransakcje.employeeManagement.service;

import pracaDomowaTransakcje.employeeManagement.exception.NotFindEmployeeException;
import pracaDomowaTransakcje.employeeManagement.model.Employee;
import pracaDomowaTransakcje.employeeManagement.model.JobPosition;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeService {

    private Connection connection;

    public EmployeeService(Connection connection) {
        this.connection = connection;
    }

    public void hireEmployee(Employee employee) throws SQLException {


        try (PreparedStatement prepStm = connection.prepareStatement("select * from Emp where name = ? and surname = ?")) {
            prepStm.setString(1, employee.getName());
            prepStm.setString(2, employee.getSurname());

            ResultSet resultSet = prepStm.executeQuery();

            if (resultSet.next()) {
                int employeeId = resultSet.getInt("employeeId");
                try (PreparedStatement updateStatement = connection.prepareStatement("UPDATE Emp SET isFired = ? WHERE employeeId = ?")) {
                    updateStatement.setBoolean(1, false);
                    updateStatement.setInt(2, employeeId);

                    int updatedRows = updateStatement.executeUpdate();
                    System.out.println("Zatrudniono ponownie pracownika: " + updatedRows);
                }
            } else {
                insertNewEmployee(employee);
            }
        }

    }

    public void insertNewEmployee(Employee employee) throws SQLException {
        try (PreparedStatement prepStm2 = connection.prepareStatement("insert into Emp (name, surname, jobPosition, salary, isFired) values (?, ?, ?, ?, ?);")) {
            prepStm2.setString(1, employee.getName());
            prepStm2.setString(2, employee.getSurname());
            prepStm2.setString(3, employee.getPosition().name());
            prepStm2.setDouble(4, employee.getSalary());
            prepStm2.setBoolean(5, employee.isFired());

            int insertedRows = prepStm2.executeUpdate();
            System.out.println("Dodano nowego pracownika: " + insertedRows);
        }
    }
    public void fireEmployee(int employeeId) throws SQLException {
        try (PreparedStatement prepStm = connection.prepareStatement("update Emp set isFired = ? where employeeId = ?")) {
            prepStm.setBoolean(1, true);
            prepStm.setInt(2, employeeId);

            int updatedRows = prepStm.executeUpdate();
            System.out.println("Zwolniono pracownika: " + updatedRows);
        }

    }

    public void increaseSalary(int employeeId, double salaryAmount) throws SQLException {

        try (PreparedStatement prepStm = connection.prepareStatement("UPDATE Emp SET salary = (salary + ?) WHERE employeeId = ?")) {
            prepStm.setDouble(1, salaryAmount);
            prepStm.setInt(2, employeeId);

            int updatedRows = prepStm.executeUpdate();
            System.out.println("Podwyzszono pensje: " + updatedRows);
        }

    }

    public void decreaseSalary(int employeeId, double salaryAmount) throws SQLException {

        try (PreparedStatement prepStm = connection.prepareStatement("UPDATE Emp SET salary = (salary - ?) WHERE employeeId = ?")) {
            prepStm.setDouble(1, salaryAmount);
            prepStm.setInt(2, employeeId);

            int updatedRows = prepStm.executeUpdate();
            System.out.println("Obnizono pensje: " + updatedRows);
        }

    }



    public void promotedPosition(int employeeId, JobPosition newPosition) throws SQLException {




        try (PreparedStatement prepStm = connection.prepareStatement("UPDATE Emp SET jobPosition = ? WHERE employeeId = ?")) {
            prepStm.setString(1, newPosition.name());
            prepStm.setInt(2, employeeId);

            int updatedRows = prepStm.executeUpdate();
            System.out.println("Awasowano pracownika: " + updatedRows);
        }

    }



    public void findEmployee(String name, String surname) throws SQLException {

        try (PreparedStatement prepStm = connection.prepareStatement("select * from Emp where name = ? and surname = ?")) {
            prepStm.setString(1, name);
            prepStm.setString(2, surname);

            ResultSet resultSet = prepStm.executeQuery();

            if (resultSet.next()) {
                int employeeId = resultSet.getInt("employeeId");
                String foundName = resultSet.getString("name");
                String foundSurname = resultSet.getString("surname");
                String jobPosition = resultSet.getString("jobPosition");
                double salary = resultSet.getDouble("salary");
                boolean isFired = resultSet.getBoolean("isFired");

                System.out.println("Znaleziono pracownika: " + foundName + " " + foundSurname + " id:" + employeeId + ", stanowisko: " + jobPosition + ", wynagrodzenie: " + salary + ", jest zwolniony? " + isFired);
            } else {
                throw new NotFindEmployeeException("Nie znaleziono pracownika, albo wpisano zle dane");
            }

        }


    }
}
