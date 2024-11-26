package matejbangievski.webaud.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
//@AllArgsConstructor - mislam nema potreba od site
@Entity
@NoArgsConstructor
@Table(name = "shop_users")
public class User {

    @Id
    String username;

    String password;

    String name;
    String surname;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    //The ShoppingCart entity is responsible for managing the relationship.
    //All ShoppingCart instances associated with a User are fetched eagerly when the User is loaded.
    private List<ShoppingCart> carts;

    public User(String username, String password, String name, String surname) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }
}
