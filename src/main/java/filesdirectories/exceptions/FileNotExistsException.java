package filesdirectories.exceptions;

public class FileNotExistsException extends RuntimeException {

    public FileNotExistsException(String message) {
        super(message);
    }

    public FileNotExistsException() {
        super("Файл с данным путём не существует");
    }

}
