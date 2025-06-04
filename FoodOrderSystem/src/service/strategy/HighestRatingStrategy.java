package service.strategy;

import entity.OrderItem;
import entity.Restaurant;
import exceptions.RestaurantNotFoundException;

import java.util.Comparator;
import java.util.List;

public class HighestRatingStrategy implements SelectionStrategy{
    @Override
    public Restaurant select(List<Restaurant> candidates, List<OrderItem> items) {
        return candidates.stream()
                .max(Comparator.comparingDouble(r -> r.rating))
                .orElseThrow(() -> new RestaurantNotFoundException("No restaurant found for highest rating criteria"));
    }
}
