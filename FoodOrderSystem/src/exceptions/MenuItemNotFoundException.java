package exceptions;

public class MenuItemNotFoundException extends RuntimeException {
    public MenuItemNotFoundException(String msg) {
        super(msg);
    }
}
