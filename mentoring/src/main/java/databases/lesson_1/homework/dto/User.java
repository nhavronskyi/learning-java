package databases.lesson_1.homework.dto;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(generator = "increment")
    private Long id;
    private String name;
    @Column(unique = true)
    private String email;
    private Double balance;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Order> orders;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
        this.balance = 0.0;
        this.orders = new ArrayList<>();
    }

    private void setBalance(Double balance) {
        if (balance < 0) {
            throw new IllegalArgumentException("Balance cannot be negative");
        }
        this.balance = balance;
    }

    public void depositMoney(Double amount) {
        setBalance(this.balance + amount);
    }

    public void withdrawMoney(Double amount) {
        setBalance(this.balance - amount);
    }
}
