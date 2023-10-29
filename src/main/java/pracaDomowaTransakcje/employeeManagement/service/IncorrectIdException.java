package pracaDomowaTransakcje.employeeManagement.service;

public class IncorrectIdException extends RuntimeException {
    public IncorrectIdException(String message) {
        super(message);
    }
}
