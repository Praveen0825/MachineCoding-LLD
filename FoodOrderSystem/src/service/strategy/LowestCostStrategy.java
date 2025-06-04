package service.strategy;

import entity.OrderItem;
import entity.Restaurant;
import exceptions.RestaurantNotFoundException;

import java.util.Comparator;
import java.util.List;

public class LowestCostStrategy implements SelectionStrategy{
    @Override
    public Restaurant select(List<Restaurant> candidates, List<OrderItem> items) {
        Restaurant bestRestaurant = null;
        int minCost = Integer.MAX_VALUE;

        for (Restaurant r : candidates) {
            int cost = r.calculateTotalCost(items);
            if (cost < minCost) {
                minCost = cost;
                bestRestaurant = r;
            }
        }

        if (bestRestaurant == null) {
            throw new RestaurantNotFoundException("No restaurant found for lowest cost criteria");
        }

        return bestRestaurant;
    }
}
