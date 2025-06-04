import entity.platform.ConsolePlatform;
import entity.platform.NetworkPlatform;
import entity.platform.TextFilePlatform;
import enums.LogLevel;
import exceptions.LoggingException;
import service.LoggerService;

public class Main {
    public static void main(String[] args) throws Exception {

        LoggerService log =new LoggerService();
        ConsolePlatform console=new ConsolePlatform();
        TextFilePlatform textPlatform=new TextFilePlatform();
        NetworkPlatform networkPlatform=new NetworkPlatform();
        log.setLevel(LogLevel.INFO);
        log.attachPlatform(LogLevel.INFO,console);
        log.attachPlatform(LogLevel.WARNING,console);
        log.attachPlatform(LogLevel.INFO,networkPlatform);
        log.attachPlatform(LogLevel.DEBUG,textPlatform);
        //log.setLevel(LogLevel.WARNING);

        //log.attachPlatform(LogLevel.ERROR,networkPlatform);
        log.attachPlatform(LogLevel.ERROR,textPlatform);
        //log.detachPlatform(LogLevel.WARNING,console);


        log.info("This is my first log with Id: {}", 101);
        log.error("I got the error: {}", "Runtime error");
        log.debug("This is just to debug with Id: {}", 503);
        //log.detachPlatform(LogLevel.DEBUG,textPlatform);
        log.debug("This is just to debug with Id: {}", 505);
        log.warning("This is just to warning with Id: {}", 505);
        log.attachPlatform(LogLevel.ERROR,networkPlatform);
        log.error("I got the error: {}", "Compiletime error");
        //log.setLevel(LogLevel.WARNING);

        System.out.println("\n ... Text File Logs .....");
        textPlatform.getLogs().forEach(System.out::println);

        System.out.println("\n ... Network Logs .....");

        networkPlatform.getLogs().forEach(System.out::println);



    }
}