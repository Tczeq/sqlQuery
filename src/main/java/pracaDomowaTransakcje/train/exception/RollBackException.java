package pracaDomowaTransakcje.train.exception;

public class RollBackException extends RuntimeException {
    public RollBackException(String message) {
        super(message);
    }
}
