package entity;

import enums.Direction;

public class Request {
    private int srcFloor;
    private int destFloor;
    private Direction dir;

    public Request(int srcFloor, int destFloor) {
        this.srcFloor = srcFloor;
        this.destFloor = destFloor;
        this.dir = (destFloor > srcFloor) ? Direction.UP : Direction.DOWN;
    }

    public int getSrcFloor() {
        return srcFloor;
    }

    public int getDestFloor() {
        return destFloor;
    }

    public Direction getDir() {
        return dir;
    }
}
