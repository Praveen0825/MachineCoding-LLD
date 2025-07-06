package vendingStates;

import entity.Item;
import service.VendingMachine;

public class DispenseState implements State{
    private VendingMachine vendingMachine;

    public DispenseState(VendingMachine vendingMachine) {
        this.vendingMachine=vendingMachine;
    }

    @Override
    public void insertCoin(int amount) throws Exception{
        throw new Exception("Currently dispensing. Please wait.");
    }

    @Override
    public void selectItem(int itemId) throws Exception{
         throw new Exception("Already selected item. Dispensing.");
    }

    @Override
    public void dispense() throws Exception{
        Item item = vendingMachine.getSelectedItem();
        vendingMachine.deductMoney(item.getPrice());
        vendingMachine.getInventory().updateStock(item.getId(), -1);
        System.out.println("Dispensing: " + item.getName());
        vendingMachine.setSelectedItem(null);
        vendingMachine.setState(new IdleState(vendingMachine));
    }
}
