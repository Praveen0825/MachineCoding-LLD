package service;

import entity.Elevator;
import entity.Request;

import java.util.ArrayList;
import java.util.List;

public class ElevatorService {
    private List<Elevator> elevators;
    private SchedularService schedularService;

    public ElevatorService(int elevatorCnt,int capacity){
        elevators=new ArrayList<>();
        for (int i=0;i<elevatorCnt;i++){
            Elevator elevator=new Elevator(i,capacity);
            elevators.add(elevator);
            new Thread(elevator).start();
        }
        schedularService=new SchedularService(elevators);
    }
    public void requestElevator(int src,int dest){
        Request request=new Request(src,dest);
        schedularService.schedule(request);
    }
}
