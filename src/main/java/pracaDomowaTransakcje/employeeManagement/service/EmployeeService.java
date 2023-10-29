package pracaDomowaTransakcje.employeeManagement.service;

import pracaDomowaTransakcje.employeeManagement.exception.IncorrectAmountException;
import pracaDomowaTransakcje.employeeManagement.exception.IncorrectIdException;
import pracaDomowaTransakcje.employeeManagement.exception.IncorrectJobPositionException;
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
                    System.out.println("Employee rehired: " + updatedRows);
                }
            } else {
                insertNewEmployee(employee);
            }
        }

    }

    public void insertNewEmployee(Employee employee) throws SQLException {
        try (PreparedStatement prepStm = connection.prepareStatement("insert into Emp (name, surname, jobPosition, salary, isFired) values (?, ?, ?, ?, ?)")) {
            prepStm.setString(1, employee.getName());
            prepStm.setString(2, employee.getSurname());
            prepStm.setString(3, employee.getPosition().name());
            prepStm.setDouble(4, employee.getSalary());
            prepStm.setBoolean(5, employee.isFired());

            int insertedRows = prepStm.executeUpdate();
            System.out.println("New employee hired: " + insertedRows);
        }
    }

    public void fireEmployee(int employeeId) throws SQLException {
        if (employeeId < 0) {
            throw new IncorrectIdException("Incorrect value");
        }
        try (PreparedStatement prepStm = connection.prepareStatement("update Emp set isFired = ? where employeeId = ?")) {
            prepStm.setBoolean(1, true);
            prepStm.setInt(2, employeeId);

            int updatedRows = prepStm.executeUpdate();
            if(updatedRows > 0) {
                System.out.println("Employee fired: " + updatedRows);
            } else {
                System.out.println("Wrong ID");
            }
        }

    }

    public void increaseSalary(int employeeId, double salaryAmount) throws SQLException {
        if (employeeId < 0) {
            throw new IncorrectIdException("Incorrect value");
        }
        if (salaryAmount < 0) {
            throw new IncorrectAmountException("Incorrect value of amount");
        }

        try (PreparedStatement prepStm = connection.prepareStatement("UPDATE Emp SET salary = (salary + ?) WHERE employeeId = ?")) {
            prepStm.setDouble(1, salaryAmount);
            prepStm.setInt(2, employeeId);

            int updatedRows = prepStm.executeUpdate();

            if(updatedRows > 0) {
                System.out.println("Salary increased: " + updatedRows);
            } else {
                System.out.println("Wrong ID");
            }
        }

    }

    public void reduceSalary(int employeeId, double salaryAmount) throws SQLException {
        if (employeeId < 0) {
            throw new IncorrectIdException("Incorrect value");
        }
        if (salaryAmount < 0) {
            throw new IncorrectAmountException("Incorrect value of amount");
        }

        try (PreparedStatement prepStm = connection.prepareStatement("UPDATE Emp SET salary = (salary - ?) WHERE employeeId = ?")) {
            prepStm.setDouble(1, salaryAmount);
            prepStm.setInt(2, employeeId);

            int updatedRows = prepStm.executeUpdate();
            if(updatedRows > 0) {
                System.out.println("Salaries reduced: " + updatedRows);
            } else {
                System.out.println("Wrong ID");
            }
        }

    }


    public void promotedPosition(int employeeId, JobPosition newPosition) throws SQLException {
        if (employeeId < 0) {
            throw new IncorrectIdException("Incorrect value");
        }

        if (newPosition == null) {
            throw new IncorrectJobPositionException("Wrong name of job Position");
        }

        try (PreparedStatement prepStm = connection.prepareStatement("UPDATE Emp SET jobPosition = ? WHERE employeeId = ?")) {
            prepStm.setString(1, newPosition.name());
            prepStm.setInt(2, employeeId);

            int updatedRows = prepStm.executeUpdate();


            if(updatedRows > 0) {
                System.out.println("Employee promoted: " + updatedRows);
            } else {
                System.out.println("Wrong ID");
            }
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

                System.out.println("Employee found: " + foundName + " " + foundSurname + " id:" + employeeId + ", position: " + jobPosition + ", salary: " + salary + ", is fired? " + isFired);
            } else {
                throw new NotFindEmployeeException("Employee not found, or wrong data entered");
            }

        }


    }
}
