package entity;

import java.util.HashSet;
import java.util.Set;

public class Agent {
    private String name;
    private Set<Integer> pincodes;

    public Agent(String name, Integer pincode) {
        this.name = name;
        this.pincodes = new HashSet<>();
        this.pincodes.add(pincode);
    }

    public String getName() {
        return name;
    }

    public Set<Integer> getPincodes() {
        return pincodes;
    }

    public void addPincodes(Integer pincode) {
        this.pincodes.add(pincode);
    }
    public boolean canDeliver(int pincode) {
        return pincodes.contains(pincode);
    }
}
