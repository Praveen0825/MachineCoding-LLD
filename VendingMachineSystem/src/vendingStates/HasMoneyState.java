package vendingStates;

import entity.Item;
import service.VendingMachine;

public class HasMoneyState implements State{
    private VendingMachine vendingMachine;

    public HasMoneyState(VendingMachine vendingMachine) {
        this.vendingMachine=vendingMachine;
    }

    @Override
    public void insertCoin(int amount) throws Exception{
        vendingMachine.addMoney(amount);
    }

    @Override
    public void selectItem(int itemId) throws Exception{
        Item item=vendingMachine.getInventory().getItem(itemId);
        if (item == null || item.getQuantity() <= 0) {
            throw new Exception("Item out of stock.");
        }

        if (item.getPrice() > vendingMachine.getCurrentBalance()) {
            throw new Exception("Insufficient balance.");
        }

        vendingMachine.setSelectedItem(item);
        vendingMachine.setState(new DispenseState(vendingMachine));
        vendingMachine.getCurrentState().dispense();
    }

    @Override
    public void dispense() throws Exception{
        throw new Exception("first select the item");
    }
}
