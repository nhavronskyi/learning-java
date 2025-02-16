package databases.lesson_1.homework.service.impl;

import databases.lesson_1.homework.dto.User;
import databases.lesson_1.homework.dto.UserOrder;
import databases.lesson_1.homework.service.OrderManagementService;
import databases.lesson_1.homework.service.UserService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserOrderManagementServiceTest {

    @Autowired
    private OrderManagementService orderManagementService;

    @Autowired
    private UserService userService;

    @Test
    @Order(0)
    void init() {
        User johnDoe = new User("john doe", "Test.User@");
        userService.createUser(johnDoe);
        userService.depositMoney(johnDoe, 5000d);
    }

    @Test
    @Transactional
    @Order(1)
    void testCreateOrderTransaction() {

        userService.findAllUsers()
                .forEach(user -> System.out.println(user.getId() + " " + user.getName() + " " + user.getEmail()));

        UserOrder userOrder = new UserOrder("Test Order", 2000d, userService.findUserById(1L));
        userOrder.setName("Test Order");

        orderManagementService.createOrder(userOrder);


        UserOrder createdUserOrder = orderManagementService.getOrder(1L);

        assertNotNull(createdUserOrder);
        assertEquals("Test Order", createdUserOrder.getName());
    }

    @Test
    @Transactional
    @Order(2)
    void testUpdateOrderTransaction() {
        UserOrder userOrder = new UserOrder("Test Order", 2000d, userService.findUserById(1L));
        orderManagementService.createOrder(userOrder);

        UserOrder createdUserOrder = orderManagementService.getOrder(1L);

        createdUserOrder.setName("Updated Test Order");
        orderManagementService.updateOrder(createdUserOrder);

        UserOrder updatedUserOrder = orderManagementService.getOrder(1L);
        assertNotNull(updatedUserOrder);
        assertEquals("Updated Test Order", updatedUserOrder.getName());
    }

    @Test
    @Transactional
    @Order(3)
    void testGetOrderTransaction() {
        UserOrder userOrder = new UserOrder("Test Order", 2000d, userService.findUserById(1L));
        orderManagementService.createOrder(userOrder);

        UserOrder createdUserOrder = orderManagementService.getOrder(1L);

        assertNotNull(createdUserOrder);
        assertEquals("Test Order", createdUserOrder.getName());
    }

    @Test
    @Transactional
    @Order(4)
    void testProceedOrderTransaction() {
        UserOrder userOrder = new UserOrder("Test Order", 2000d, userService.findUserById(1L));
        orderManagementService.createOrder(userOrder);

        UserOrder createdUserOrder = orderManagementService.getOrder(1L);

        orderManagementService.proceedOrder(createdUserOrder);

        UserOrder proceededUserOrder = orderManagementService.getOrder(1L);
        assertTrue(proceededUserOrder.isProceeded());
    }

    @Test
    @Transactional
    @Order(5)
    void testDeleteOrderTransaction() {
        UserOrder userOrder = new UserOrder("Test Order", 2000d, userService.findUserById(1L));
        orderManagementService.createOrder(userOrder);

        UserOrder createdUserOrder = orderManagementService.getOrder(1L);

        orderManagementService.deleteOrder(createdUserOrder);

        var orders = orderManagementService.getAllUserOrders(1L);
        assertTrue(orders.isEmpty());
    }
}
