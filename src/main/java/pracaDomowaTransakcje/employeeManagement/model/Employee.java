package pracaDomowaTransakcje.employeeManagement.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Employee {
    private String name;
    private String surname;
    private JobPosition position;
    private double salary;
    private boolean isFired;




}