package databases.lesson_1.homework.service;

import databases.lesson_1.homework.dto.Order;

public interface OrderManagementService {
    void createOrder(Order order);

    void updateOrder(Order order);

    void deleteOrder(Order order);

    Order getOrder(Long id);

    void proceedOrder(Order order);
}
