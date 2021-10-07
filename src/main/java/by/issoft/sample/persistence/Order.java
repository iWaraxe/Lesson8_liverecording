package by.issoft.sample.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Order {

    private UUID id;
    private List<OrderItem> orderItems = new ArrayList<>();

    public Order(UUID id) {
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<OrderItem> getOrderItems() {
        return List.copyOf(orderItems);
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
    }
}
