package filesdirectories.exceptions;

public class CannotReachFileAttributes extends RuntimeException {

    public CannotReachFileAttributes(String message) {
        super(message);
    }

    public CannotReachFileAttributes() {
        super("Невозможно получить атрибуты файла при сканировании");
    }

}
