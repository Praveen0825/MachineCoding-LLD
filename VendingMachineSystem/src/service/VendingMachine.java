package service;

import entity.Inventory;
import entity.Item;
import vendingStates.HasMoneyState;
import vendingStates.IdleState;
import vendingStates.State;

public class VendingMachine {
    private Inventory inventory=new Inventory();
    private State currentState;

    private int currentBalance=0;
    private Item selectedItem;

    public VendingMachine() {
        this.currentState = new IdleState(this);
    }

    public void insertCoin(int amount) throws Exception {
        currentState.insertCoin(amount);
    }

    public void selectItem(int id) throws Exception {
        currentState.selectItem(id);
    }

    public void addMoney(int amount) {
        currentBalance += amount;
    }

    public void deductMoney(int amount) {
        currentBalance -= amount;
    }

    public void setState(State state) {
        currentState=state;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public State getCurrentState(){
        return currentState;
    }

    public int getCurrentBalance() {
        return currentBalance;
    }

    public void setSelectedItem(Item item) {
        this.selectedItem=item;
    }

    public Item getSelectedItem() {
        return selectedItem;
    }
}
