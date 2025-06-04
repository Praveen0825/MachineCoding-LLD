package entity.platform;

import entity.LogMessage;
import exceptions.LoggingException;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class NetworkPlatform implements LoggingPlatform{
    private List<String> networkLogs=new ArrayList<>();
    private BlockingQueue<LogMessage> queue = new LinkedBlockingQueue<>();

    public NetworkPlatform() {
        Thread asyncThread = new Thread(() -> {
            try {
                while (true) {
                    LogMessage message = queue.take();
                    networkLogs.add("Network -> " + message.format());
                }
            } catch (InterruptedException ignored) {
                
            }
        });
        asyncThread.setDaemon(true);
        asyncThread.start();
    }

    @Override
    public void log(LogMessage message) throws LoggingException {
        try{
            queue.add(message);
            //networkLogs.add("Network ->" +message.format());
            //System.out.println("Network -> " +message.format());
        } catch (Exception e){
            throw new LoggingException("fail to log in network",e);
        }
    }

    @Override
    public String getName() {
        return "Network";
    }

    public List<String> getLogs() {
        return networkLogs;
    }
}
