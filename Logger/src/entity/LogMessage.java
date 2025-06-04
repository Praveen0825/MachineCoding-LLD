package entity;

import enums.LogLevel;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class LogMessage {
    private LogLevel level;
    private String message;


    public LogMessage(LogLevel level, String message) {
        this.level = level;
        this.message = message;
    }

    public LogLevel getLevel() {
        return level;
    }

    public String getMessage() {
        return message;
    }


    public String format() {
        return String.format(" [%s] %s",level,message);
    }
}
