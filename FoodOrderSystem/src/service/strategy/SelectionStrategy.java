package service.strategy;

import entity.OrderItem;
import entity.Restaurant;

import java.util.List;

public interface SelectionStrategy {
    Restaurant select(List<Restaurant> candidates, List<OrderItem> items);
}
