package databases.lesson_1.homework.controller;

import databases.lesson_1.homework.dto.UserOrder;
import databases.lesson_1.homework.service.OrderManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderManagementService orderManagementService;

    @PostMapping("create-order")
    public void createOrder(@RequestBody UserOrder userOrder) {
        orderManagementService.createOrder(userOrder);
    }

    @PutMapping("update-order")
    public void updateOrder(@RequestBody UserOrder userOrder) {
        orderManagementService.updateOrder(userOrder);
    }

    @DeleteMapping("delete-order")
    public void deleteOrder(@RequestBody UserOrder userOrder) {
        orderManagementService.deleteOrder(userOrder);
    }

    @GetMapping("get-order")
    public UserOrder getOrder(@RequestParam Long id) {
        return orderManagementService.getOrder(id);
    }

    @PostMapping("proceed-order")
    public void proceedOrder(UserOrder userOrder) {
        orderManagementService.proceedOrder(userOrder);
    }
}
