package databases.lesson_1.homework.controller;

import databases.lesson_1.homework.dto.Order;
import databases.lesson_1.homework.service.OrderManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderManagementService orderManagementService;

    @PostMapping("create-order")
    public void createOrder(@RequestBody Order order) {
        orderManagementService.createOrder(order);
    }

    @PutMapping("update-order")
    public void updateOrder(@RequestBody Order order) {
        orderManagementService.updateOrder(order);
    }

    @DeleteMapping("delete-order")
    public void deleteOrder(@RequestBody Order order) {
        orderManagementService.deleteOrder(order);
    }

    @GetMapping("get-order")
    public Order getOrder(@RequestParam Long id) {
        return orderManagementService.getOrder(id);
    }

    @PostMapping("proceed-order")
    public void proceedOrder(Order order) {
        orderManagementService.proceedOrder(order);
    }
}
