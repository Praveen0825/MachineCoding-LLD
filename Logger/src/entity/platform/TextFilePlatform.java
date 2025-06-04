package entity.platform;

import entity.LogMessage;
import exceptions.LoggingException;

import java.util.ArrayList;
import java.util.List;

public class TextFilePlatform implements LoggingPlatform{
    private List<String> textLogs=new ArrayList<>();
    List<String> t=new ArrayList<>(5) , ad=new ArrayList<>(5);
    @Override
    public void log(LogMessage message) throws LoggingException {
        try{
            textLogs.add("TextFile ->" +message.format());
            //System.out.println("TextFile -> " +message.format());
        } catch (Exception e){
            throw new LoggingException("fail to log in text file",e);
        }
    }

    @Override
    public String getName() {
        return "TextFile";
    }

    public List<String> getLogs() {
        return textLogs;
    }
}
