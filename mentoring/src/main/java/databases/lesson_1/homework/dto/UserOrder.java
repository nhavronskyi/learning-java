package databases.lesson_1.homework.dto;

import jakarta.annotation.Generated;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
public class UserOrder {
    @Id
    @Generated(value = "increment")
    private Long id;
    @Nonnull
    private String name;
    private Double price;
    private boolean isProceeded;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public UserOrder(@Nonnull String name, Double price, User user) {
        this.name = name;
        this.price = price;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", userId=" + user.getId() +
                '}';
    }
}
