package databases.lesson_1.homework.service.impl;

import databases.lesson_1.homework.dto.Order;
import databases.lesson_1.homework.dto.User;
import databases.lesson_1.homework.repository.OrderRepository;
import databases.lesson_1.homework.repository.UserRepository;
import databases.lesson_1.homework.service.OrderManagementService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderManagementServiceImpl implements OrderManagementService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void createOrder(Order order) {
        Optional.ofNullable(order)
                .filter(o -> Optional.ofNullable(o.getUser())
                        .map(User::getId)
                        .map(userRepository::findById)
                        .isPresent()
                ).ifPresentOrElse(
                        o -> {
                            Long maxId = orderRepository.findMaxId().orElse(0L);
                            o.setId(maxId + 1);
                            orderRepository.save(o);
                            User user = o.getUser();
                            user.getOrders().add(o);
                            userRepository.save(user);
                        },
                        () -> {
                            throw new IllegalArgumentException("User not found");
                        }
                );
    }

    @Override
    @Transactional
    public void updateOrder(Order order) {
        Optional.ofNullable(order)
                .filter(o -> orderRepository.findById(order.getId())
                        .isPresent())
                .ifPresentOrElse(
                        orderRepository::save,
                        () -> {
                            throw new IllegalArgumentException("Order not found");
                        }
                );
    }

    @Override
    public void deleteOrder(Order order) {
        Optional.ofNullable(order)
                .filter(o -> orderRepository.findById(order.getId()).isPresent())
                .ifPresentOrElse(
                        orderRepository::delete,
                        () -> {
                            throw new IllegalArgumentException("Order not found");
                        }
                );
    }

    @Override
    public Order getOrder(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
    }

    @Override
    public void proceedOrder(Order order) {
        Optional.ofNullable(order)
                .filter(o -> orderRepository.findById(order.getId()).isPresent())
                .ifPresentOrElse(
                        o -> {
                            o.getUser().withdrawMoney(o.getPrice());
                            userRepository.save(o.getUser());
                            orderRepository.deleteById(o.getId());
                        },
                        () -> {
                            throw new IllegalArgumentException("Order not found");
                        }
                );
    }
}
