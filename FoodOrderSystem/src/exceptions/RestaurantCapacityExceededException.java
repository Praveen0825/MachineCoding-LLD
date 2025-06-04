package exceptions;

public class RestaurantCapacityExceededException extends RuntimeException {
    public RestaurantCapacityExceededException(String msg) {
        super(msg);
    }
}
