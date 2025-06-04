import entity.OrderItem;
import entity.User;
import enums.SelectionStrategyType;
import service.OrderService;
import service.RestaurantService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        RestaurantService restaurantService = new RestaurantService();
        OrderService orderService = new OrderService(restaurantService);

        // Onboarding restaurants
        restaurantService.onboardRestaurant("R1", 5, 4.5);
        restaurantService.updateMenu("R1", "Veg Biryani", 100);
        restaurantService.updateMenu("R1", "Chicken Biryani", 150);

        restaurantService.onboardRestaurant("R2", 5, 4.0);
        restaurantService.updateMenu("R2", "Chicken Biryani", 175);
        restaurantService.updateMenu("R2", "Idli", 10);
        restaurantService.updateMenu("R2", "Dosa", 50);
        restaurantService.updateMenu("R2", "Veg Biryani", 80);

        restaurantService.onboardRestaurant("R3", 1, 4.9);
        restaurantService.updateMenu("R3", "Gobi Manchurian", 150);
        restaurantService.updateMenu("R3", "Idli", 15);
        restaurantService.updateMenu("R3", "Chicken Biryani", 175);
        restaurantService.updateMenu("R3", "Dosa", 30);

        // Orders
        User ashwin = new User("Ashwin");
        orderService.placeOrder(ashwin, List.of(new OrderItem("Idli", 3), new OrderItem("Dosa", 1)), SelectionStrategyType.LOWEST_COST);

        User harish = new User("Harish");
        orderService.placeOrder(harish, List.of(new OrderItem("Idli", 3), new OrderItem("Dosa", 1)), SelectionStrategyType.LOWEST_COST);

        User shruthi = new User("Shruthi");
        orderService.placeOrder(shruthi, List.of(new OrderItem("Veg Biryani", 3)), SelectionStrategyType.HIGHEST_RATING);

        orderService.markCompleted(1);

        orderService.placeOrder(harish, List.of(new OrderItem("Idli", 3), new OrderItem("Dosa", 1)), SelectionStrategyType.LOWEST_COST);

        User xyz = new User("Xyz");
        orderService.placeOrder(xyz, List.of(new OrderItem("Paneer Tikka", 1), new OrderItem("Idli", 1)), SelectionStrategyType.LOWEST_COST);
    }
}