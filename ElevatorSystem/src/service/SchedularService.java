package service;

import entity.Elevator;
import entity.Request;

import java.util.List;

public class SchedularService {
    private List<Elevator> elevators;

    public SchedularService(List<Elevator> elevators) {
        this.elevators = elevators;
    }
    public void schedule(Request request){
        Elevator bestElevator = null;
        int minDist=Integer.MAX_VALUE;

        for(Elevator elevator: elevators){
            int distance=Math.abs(elevator.currentFloor()-request.getSrcFloor());
            if(distance<minDist){
                minDist=distance;
                bestElevator=elevator;
            }
        }

        if(bestElevator!=null){
            bestElevator.addRequest(request);
        }
    }
}
