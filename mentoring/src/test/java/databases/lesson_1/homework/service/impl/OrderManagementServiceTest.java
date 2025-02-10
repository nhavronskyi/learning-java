package databases.lesson_1.homework.service.impl;

import databases.lesson_1.homework.dto.Order;
import databases.lesson_1.homework.dto.User;
import databases.lesson_1.homework.service.OrderManagementService;
import databases.lesson_1.homework.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class OrderManagementServiceTest {

    @Autowired
    private OrderManagementService orderManagementService;

    @Autowired
    private UserService userService;

    @BeforeEach
    void setUp() {
        if (userService.findAllUsers().isEmpty()) {
            userService.createUser(new User("john doe", "Test.User@"));
        }
    }

    @Test
    @Transactional
    void testCreateOrderTransaction() {

        userService.findAllUsers()
                .forEach(user -> System.out.println(user.getId() + " " + user.getName() + " " + user.getEmail()));

        Order order = new Order("Test Order", 2000d, userService.findUserById(1L));
        order.setName("Test Order");

        orderManagementService.createOrder(order);


        Order createdOrder = orderManagementService.getOrder(1L);

        System.out.println(createdOrder);

        assertNotNull(createdOrder);
        assertEquals("Test Order", createdOrder.getName());
    }

    @Test
    @Transactional
    void testUpdateOrderTransaction() {
        Order order = new Order();
        orderManagementService.updateOrder(order);
        // Add assertions to verify the transaction behavior
    }

    @Test
    @Transactional
    void testDeleteOrderTransaction() {
        Order order = new Order();
        orderManagementService.deleteOrder(order);
        // Add assertions to verify the transaction behavior
    }

    @Test
    @Transactional
    void testGetOrderTransaction() {
        Long id = 1L;
        Order order = orderManagementService.getOrder(id);
        assertNotNull(order);
        // Add assertions to verify the transaction behavior
    }

    @Test
    @Transactional
    void testProceedOrderTransaction() {
        Order order = new Order();
        orderManagementService.proceedOrder(order);
        // Add assertions to verify the transaction behavior
    }
}
