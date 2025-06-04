package service;

import config.LoggerConfig;
import entity.LogMessage;
import entity.platform.LoggingPlatform;
import enums.LogLevel;
import exceptions.LoggingException;
import exceptions.PlatformNotAvailable;

import java.util.HashSet;
import java.util.Set;

public class LoggerService {
    private final LoggerConfig config = LoggerConfig.getInstance();

    public void log(LogLevel level, String message, Object... args) throws LoggingException {
        if(level.getLevel() < config.getCurrlevel().getLevel())
            return ;

        String formattedMessage=formatMessage(message,args);
        LogMessage logMessage=new LogMessage(level,formattedMessage);
        for (LoggingPlatform platform : config.getPlatforms(level)) {
            try {
                platform.log(logMessage);
            } catch (LoggingException e) {
                throw new LoggingException("logging exception: " + e.getMessage(), e);
            }
        }


    }

    private String formatMessage(String str, Object... args){
        for(Object arg:args){
            str=str.replaceFirst("\\{}",arg.toString());
        }
        return str;
    }

    public void setLevel(LogLevel level){
        config.setLogLevel(level);
    }

    public void attachPlatform(LogLevel level, LoggingPlatform platform){
        config.attachPlatform(level,platform);
    }

    public void detachPlatform(LogLevel level, LoggingPlatform platform) throws Exception {
        config.detachPlatform(level,platform);
    }

    public void attachPlatform(LoggingPlatform platform){
        config.attachPlatform(config.getCurrlevel(),platform);
    }

    public void detachPlatform(LoggingPlatform platform) throws Exception {
        config.detachPlatform(config.getCurrlevel(),platform);
    }

    public void info(String msg, Object... args) throws LoggingException {
        log(LogLevel.INFO,msg,args);
    }

    public void debug(String msg,Object... args) throws LoggingException {
        log(LogLevel.DEBUG,msg,args);
    }

    public void warning(String msg,Object... args) throws LoggingException {
        log(LogLevel.WARNING,msg,args);
    }
    public void error(String msg,Object... args) throws LoggingException {
        log(LogLevel.ERROR,msg,args);
    }

}
