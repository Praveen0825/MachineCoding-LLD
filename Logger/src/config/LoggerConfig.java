package config;

import entity.platform.LoggingPlatform;
import enums.LogLevel;
import exceptions.PlatformNotAvailable;

import java.util.*;

public class LoggerConfig {
    private static LoggerConfig instance;
    private LogLevel currlevel;
    private Map<LogLevel, Set<LoggingPlatform>>  platformMap;

    private LoggerConfig() {
        this.currlevel = LogLevel.DEBUG;
        this.platformMap = new HashMap<>();
    }

    public static LoggerConfig getInstance(){
        if(instance==null) {
            synchronized (LoggerConfig.class) {
                if(instance==null)
                    instance = new LoggerConfig();
            }
        }
        return instance;
    }
    public void setLogLevel(LogLevel level){
        currlevel=level;
    }

    public LogLevel getCurrlevel() {
        return currlevel;
    }

    public Set<LoggingPlatform> getPlatforms(LogLevel level) {
        return platformMap.getOrDefault(level,Collections.emptySet());
    }

    public void attachPlatform(LogLevel level,LoggingPlatform platform){
        platformMap.computeIfAbsent(level,key-> new HashSet<>()).add(platform);
    }
    public void detachPlatform(LogLevel level,LoggingPlatform platform) throws PlatformNotAvailable {
        Set<LoggingPlatform> platforms = platformMap.get(level);
        if (platforms == null) {
            throw new PlatformNotAvailable("Platform not available in specified level to detach");
        }
        platforms.remove(platform);
    }


}
