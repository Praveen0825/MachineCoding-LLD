package service;

import entity.Order;
import entity.OrderItem;
import entity.Restaurant;
import entity.User;
import enums.OrderStatus;
import enums.SelectionStrategyType;
import exceptions.OrderNotFoundException;
import exceptions.RestaurantNotFoundException;
import service.strategy.SelectionStrategy;
import service.strategy.SelectionStrategyFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderService {
    RestaurantService restaurantService;
    Map<Integer, Order> orders=new HashMap<>();

    public OrderService(RestaurantService restaurantService){
        this.restaurantService=restaurantService;
    }
    public Order placeOrder(User user, List<OrderItem> items, SelectionStrategyType type){
        List<Restaurant> restaurants=restaurantService.getAll().stream()
                .filter((r->r.hasAllItems(items)&&r.canAcceptOrder()))
                .toList();
        if(restaurants.isEmpty()){
            throw new RestaurantNotFoundException("No Restuarant available now for this order");
        }
        SelectionStrategy strategy=SelectionStrategyFactory.getStrategy(type);
        Restaurant restaurant=strategy.select(restaurants,items);
        restaurant.acceptOrder();
        Order order=new Order(user,items);
        order.assignRestaurant(restaurant);
        orders.put(order.id,order);
        System.out.println("Order ID " +order.id+ " assigned to "+ restaurant.name);
        return order;

    }

    public void markCompleted(int orderId) {
        Order order = orders.get(orderId);
        if (order == null) throw new OrderNotFoundException("Invalid order ID");
        if (order.status != OrderStatus.ACCEPTED) {
            System.out.println("Order ID " + orderId + " is not in ACCEPTED state.");
            return;
        }
        order.complete();
        System.out.println("Order ID " + orderId + " marked as COMPLETED");
    }
}
