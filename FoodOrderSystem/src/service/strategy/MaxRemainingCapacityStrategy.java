package service.strategy;

import entity.OrderItem;
import entity.Restaurant;
import exceptions.RestaurantNotFoundException;

import java.util.Comparator;
import java.util.List;

public class MaxRemainingCapacityStrategy implements SelectionStrategy{
    @Override
    public Restaurant select(List<Restaurant> candidates, List<OrderItem> items) {
        return candidates.stream()
                .max(Comparator.comparingInt(Restaurant::getRemainingCapacity))
                .orElseThrow(() -> new RestaurantNotFoundException("No restaurant found for Max Remaining Capacity criteria"));
    }
}
