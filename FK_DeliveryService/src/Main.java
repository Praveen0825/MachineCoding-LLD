import service.AgentService;
import service.DeliveryService;
import service.OrderService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) throws Exception {
        OrderService orderService = OrderService.getInstance();
        AgentService agentService = AgentService.getInstance();
        DeliveryService deliveryService = DeliveryService.getInstance();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm - MMM dd, yyyy");

        // Create orders
        orderService.createOrder("OrderA", 560087);
        orderService.createOrder("OrderB", 560088);
        orderService.createOrder("OrderC", 560089);
        orderService.createOrder("OrderD", 560087);

        // Scheduled order
        orderService.createOrder("OrderE", 560087, LocalDateTime.parse("10:30 - Mar 22, 2025", formatter), 30);

        // Create agents
        agentService.createAgent("AgentA", 560087);
        agentService.createAgent("AgentB", 560088);
        agentService.createAgent("AgentC", 560089);

        // Execute delivery
        deliveryService.executeDeliveries();
    }
}
