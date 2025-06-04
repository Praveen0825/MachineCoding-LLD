package service;

import entity.Agent;
import entity.Order;
import enums.OrderStatus;
import util.DateUtil;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class DeliveryService {
    private static volatile DeliveryService instance;

    private DeliveryService(){}

    public static DeliveryService getInstance(){
        if(instance==null){
            synchronized (DeliveryService.class){
                if(instance==null){
                    instance=new DeliveryService();
                }
            }
        }
        return  instance;
    }

    public void executeDeliveries()throws Exception{
        AgentService.getInstance().getAllAgents()
                .forEach(agent -> {
                    Collection<Order> orders = OrderService.getInstance()
                            .getAllOrders()
                            .stream()
                            .filter(order -> order.getStatus() == OrderStatus.CREATED)
                            .filter(order -> agent.canDeliver(order.getPincode()))
                            .toList();

                    orders.forEach(order -> {
                        order.setStatus(OrderStatus.PICKED_UP);
                        if (order.isScheduled()) {
                            LocalDateTime start = order.getScheduledTime();
                            LocalDateTime end = start.plusMinutes(order.getDurationMinutes());
                            System.out.println("Agent " + agent.getName() + " has picked up Order " + order.getName()
                                    + " at " + DateUtil.format(start));
                            order.setStatus(OrderStatus.DELIVERED);
                            System.out.println("Agent " + agent.getName() + " has completed delivery of Order " + order.getName()
                                    + " to " + order.getPincode() + " at " + DateUtil.format(end));
                        } else {
                            order.setStatus(OrderStatus.DELIVERED);
                            System.out.println("Agent " + agent.getName() + " has picked up Order " + order.getName());
                            System.out.println("Agent " + agent.getName() + " has delivered Order " + order.getName()
                                    + " to " + order.getPincode());
                        }
                    });
                });
    }
}
