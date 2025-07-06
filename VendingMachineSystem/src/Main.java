import entity.Item;
import service.VendingMachine;

public class Main {
    public static void main(String[] args) throws Exception {
        // Step 1: Setup Vending Machine
        VendingMachine vm = new VendingMachine();

        // Step 2: Add items
        vm.getInventory().addItem(new Item(1, "Coke", 25, 5));
        vm.getInventory().addItem(new Item(2, "Pepsi", 35, 3));
        vm.getInventory().addItem(new Item(3, "Water", 20, 10));

        // Step 3: Display all items
        System.out.println("Available Items:");
        for (Item item : vm.getInventory().getAllItems()) {
            System.out.println(item.getId() + ". " + item.getName() + " - $" + item.getPrice() + " [" + item.getQuantity() + " in stock]");
        }

        // Step 4: Simulate user inserting coins
        System.out.println("\nUser inserts $50...");
        vm.insertCoin(50); // User inserts $50

        // Step 5: User selects item
        System.out.println("User selects item: 2 (Pepsi)");
        vm.selectItem(2);

        // Step 6: Check balance after purchase
        System.out.println("Remaining balance: $" + vm.getCurrentBalance());
    }
}