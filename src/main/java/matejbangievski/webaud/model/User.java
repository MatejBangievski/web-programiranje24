package matejbangievski.webaud.model;

import lombok.Data;

@Data
//@AllArgsConstructor - mislam nema potreba od site
public class User {
    String username;
    String password;

    String name;
    String surname;

    public User(String username, String password, String name, String surname) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }
}
