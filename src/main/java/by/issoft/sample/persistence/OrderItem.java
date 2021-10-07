package by.issoft.sample.persistence;

import java.util.UUID;

public class OrderItem {

    private final UUID id;

    private final UUID productId;

    private final UUID OrderId;

    public OrderItem(UUID id, UUID productId, UUID orderId) {
        this.id = UUID.randomUUID();
        this.productId = productId;
        OrderId = orderId;
    }

    public UUID getId() {
        return id;
    }

    public UUID getProductId() {
        return productId;
    }

    public UUID getOrderId() {
        return OrderId;
    }
}
