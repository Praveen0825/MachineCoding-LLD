package vendingStates;

import service.VendingMachine;

public class IdleState implements State{
    private VendingMachine vendingMachine;

    public IdleState(VendingMachine vendingMachine) {
        this.vendingMachine=vendingMachine;
    }

    @Override
    public void insertCoin(int amount) throws Exception{
        vendingMachine.addMoney(amount);
        vendingMachine.setState(new HasMoneyState(vendingMachine));
    }

    @Override
    public void selectItem(int itemId) throws Exception{
        throw new Exception("first insert Coin");
    }

    @Override
    public void dispense() throws Exception{
        throw new Exception("first insert Coin and select item");
    }
}
