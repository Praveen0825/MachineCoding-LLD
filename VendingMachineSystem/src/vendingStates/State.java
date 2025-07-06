package vendingStates;

public interface State {
    void insertCoin(int amount) throws Exception;
    void selectItem(int itemId) throws Exception;
    void dispense() throws Exception;
}
