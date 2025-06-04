package util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
    private static final DateTimeFormatter formatter =DateTimeFormatter.ofPattern("hh:mm a, MMM dd, yyyy");
    public static String format(LocalDateTime dateTime){
        return dateTime.format(formatter);
    }
}
