package by.issoft.sample.persistence;

import java.util.List;
import java.util.UUID;

public interface OrderStorage {
    void persist(Order order);

    Order loadById(UUID id);

    List<Order> loadByUserId(UUID id);
}
