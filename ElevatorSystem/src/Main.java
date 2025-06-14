import service.ElevatorService;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        ElevatorService system = new ElevatorService(3, 5);

        system.requestElevator(0, 5);
        system.requestElevator(3, 1);
        system.requestElevator(2, 7);
    }
}