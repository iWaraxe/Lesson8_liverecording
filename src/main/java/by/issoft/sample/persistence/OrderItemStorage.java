package by.issoft.sample.persistence;

import java.util.List;
import java.util.UUID;

public interface OrderItemStorage {
    void persist(OrderItem orderItem);

    List<OrderItem> loadByOrderId(UUID orderId);
}
