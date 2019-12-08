package filesdirectories.exceptions;

public class NotDirectoryException extends RuntimeException {

    public NotDirectoryException(String message) {
        super(message);
    }

    public NotDirectoryException() {
        super("Файл не является директорией");
    }

}
