package entity;

import enums.Direction;
import enums.ElevatorState;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Elevator implements Runnable{
    private final int id;
    private int currentFloor=0;
    private Direction direction=Direction.IDLE;
    private ElevatorState state=ElevatorState.IDLE;
    private int capacity;
    private int currentLoad=0;
    private Queue<Request> reqQueue=new ConcurrentLinkedQueue<>();
    private ReentrantLock lock=new ReentrantLock();
    private Condition notEmpty = lock.newCondition();

    public Elevator(int id, int capacity) {
        this.id = id;
        this.capacity = capacity;
    }

    @Override
    public void run() {
        while (true) {
            lock.lock();
            try {
                while (reqQueue.isEmpty()) {
                    notEmpty.await(); // wait until signaled
                }

                Request req = reqQueue.poll();
                processRequest(req);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        }
    }

    public void addRequest(Request request){

        lock.lock();
        try {
            reqQueue.add(request);
            notEmpty.signal();
        }finally {
            lock.unlock();
        }
    }

    private void processRequest(Request request) {
        moveToFloor(request.getSrcFloor());
        System.out.println("Elevator " + id + " picked up at floor " + request.getSrcFloor());

        moveToFloor(request.getDestFloor());
        System.out.println("Elevator " + id + " dropped at floor " + request.getDestFloor());

        state = ElevatorState.IDLE;
        direction = Direction.IDLE;
    }

    private void moveToFloor(int targetFloor) {
        if (targetFloor == currentFloor) return;

        state = ElevatorState.MOVING;
        direction = (targetFloor > currentFloor) ? Direction.UP : Direction.DOWN;

        while (currentFloor != targetFloor) {
            try {
                Thread.sleep(500); // simulate movement
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            currentFloor += (direction == Direction.UP) ? 1 : -1;
        }

        state = ElevatorState.STOPPED;
    }

    public int currentFloor() {
        return currentFloor;
    }
}

