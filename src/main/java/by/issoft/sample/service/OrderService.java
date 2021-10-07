package by.issoft.sample.service;

import by.issoft.sample.persistence.Order;
import by.issoft.sample.persistence.OrderItem;
import by.issoft.sample.persistence.OrderItemStorage;
import by.issoft.sample.persistence.OrderStorage;

import java.util.List;
import java.util.UUID;

public class OrderService {

    private final OrderStorage orderStorage;
    private final OrderItemStorage orderItemStorage;

    public OrderService(OrderStorage orderStorage, OrderItemStorage orderItemStorage) {
        this.orderStorage = orderStorage;
        this.orderItemStorage = orderItemStorage;
    }

    public void createOrder(Order order) {
        //validate
        orderStorage.persist(order);
        for (OrderItem item: order.getOrderItems()) {
            orderItemStorage.persist(item);
        }
    }

    public List<Order> loadByUserId(UUID id) {
        final List<Order> orders = orderStorage.loadByUserId(id);
        return orders;
    }
   // bitcask
    // 1, {id: "1", "name": "my first order"}
    // orders.json
}
