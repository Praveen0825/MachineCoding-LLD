package entity;

import exceptions.RestaurantCapacityExceededException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Restaurant {
    public String name;
    public Map<String, MenuItem> menu;
    public double rating;
    public int maxCapacity;
    int currentOrders;

    public Restaurant(String name, int maxCapacity, double rating) {
        this.name = name;
        this.maxCapacity = maxCapacity;
        this.rating = rating;
        this.menu = new HashMap<>();
        this.currentOrders = 0;
    }

    public void addOrUpdateItem(String name, int price) {
        menu.put(name, new MenuItem(name, price));
    }

    public boolean hasAllItems(List<OrderItem> orderItems) {
        for (OrderItem item : orderItems) {
            if (!menu.containsKey(item.name)) return false;
        }
        return true;
    }

    public int calculateTotalCost(List<OrderItem> items) {
        return items.stream()
                .mapToInt(item -> menu.get(item.name).price * item.quantity)
                .sum();
    }

    public boolean canAcceptOrder() {
        return currentOrders < maxCapacity;
    }

    public void acceptOrder() {
        if (!canAcceptOrder()) throw new RestaurantCapacityExceededException(name + " is at full capacity.");
        currentOrders++;
    }

    public void completeOrder() {
        if (currentOrders > 0) currentOrders--;
    }

    public int getRemainingCapacity() {
        return maxCapacity - currentOrders;
    }
}
