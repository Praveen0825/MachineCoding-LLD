package entity;

import enums.OrderStatus;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Order {
    static AtomicInteger ID_GENERATOR = new AtomicInteger(1);
    public int id;
    User user;
    List<OrderItem> items;
    Restaurant assignedRestaurant;
    public OrderStatus status;

    public Order(User user, List<OrderItem> items) {
        this.id = ID_GENERATOR.getAndIncrement();
        this.user = user;
        this.items = items;
        this.status = OrderStatus.PENDING;
    }

    public void accept(Restaurant restaurant) {
        this.assignedRestaurant = restaurant;
        this.status = OrderStatus.ACCEPTED;
    }

    public void complete() {
        this.status = OrderStatus.COMPLETED;
        this.assignedRestaurant.completeOrder();
    }
    public void assignRestaurant(Restaurant restaurant){
        this.assignedRestaurant= restaurant;
    }
}
