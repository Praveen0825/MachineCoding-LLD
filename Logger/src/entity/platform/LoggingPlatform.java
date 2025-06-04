package entity.platform;

import entity.LogMessage;
import exceptions.LoggingException;

public interface LoggingPlatform {
    void log(LogMessage message) throws LoggingException;
    String getName();
}
