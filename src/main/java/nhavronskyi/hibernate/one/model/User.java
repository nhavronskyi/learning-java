package nhavronskyi.hibernate.one.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {
    @Id
    long id;
    String name;
}
