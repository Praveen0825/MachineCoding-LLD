package entity.platform;

import entity.LogMessage;
import exceptions.LoggingException;

import java.util.ArrayList;
import java.util.List;

public class ConsolePlatform implements LoggingPlatform{
    List<String> consoleLogs=new ArrayList<>();
    @Override
    public void log(LogMessage message) throws LoggingException {
        try{
            consoleLogs.add("Console -> " +message.format());
            System.out.println("Console -> " +message.format());
        } catch (Exception e){
            throw new LoggingException("fail to console log",e);
        }
    }

    @Override
    public String getName() {
        return "Console";
    }

    public List<String> getConsoleLogs() {
        return consoleLogs;
    }
}
