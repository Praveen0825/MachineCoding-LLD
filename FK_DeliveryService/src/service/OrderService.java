package service;

import entity.Order;
import enums.OrderStatus;
import exceptions.OrderNotFoundException;

import java.time.LocalDateTime;
import java.util.*;

public class OrderService {
    private static volatile OrderService instance;
    private Map<String, Order> orders=new LinkedHashMap<>();
    private OrderService()
    {

    }
    public static OrderService getInstance(){
        if(instance==null){
            synchronized (OrderService.class){
                if(instance==null){
                    instance=new OrderService();
                }
            }
        }
        return instance;
    }

    public void createOrder(String name, int pincode){
        orders.put(name,new Order(name,pincode));
    }

    public void createOrder(String name, int pincode, LocalDateTime time,int duration){
        orders.put(name,new Order(name,pincode,time,duration));
    }

    public List<Order> getOrdersByPincode(int pincode){
        List<Order> result=new ArrayList<>();
        for(Order order: orders.values()){
            if(order.getPincode()==pincode && order.getStatus() == OrderStatus.CREATED){
                result.add(order);
            }
        }
        return result;
    }
    public  Order getOrder(String name) throws OrderNotFoundException {
        if(!orders.containsKey(name))
            throw new OrderNotFoundException("invalid order name");
        return orders.get(name);
    }
    public Collection<Order> getAllOrders(){
        return orders.values();
    }
}
