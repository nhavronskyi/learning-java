package databases.lesson_1.homework.service;

import databases.lesson_1.homework.dto.UserOrder;

import java.util.List;

public interface OrderManagementService {
    void createOrder(UserOrder userOrder);

    void updateOrder(UserOrder userOrder);

    void deleteOrder(UserOrder userOrder);

    UserOrder getOrder(Long id);

    List<UserOrder> getAllUserOrders(Long id);

    void proceedOrder(UserOrder userOrder);
}
