package matejbangievski.webaud.bootstrap;

import jakarta.annotation.PostConstruct;
import matejbangievski.webaud.model.Category;
import matejbangievski.webaud.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//in-memory data holder
@Component
public class DataHolder {
    public static List<Category> categories = null; //static ne zaboravaj
    public static List<User> users = null;

    //On application startup, initialize the categories list
    //On each startup, the list will be initialized with the same values and the previous values will be lost
    @PostConstruct
    public void init() {
        categories = new ArrayList<>();
        users = new ArrayList<>();

        categories.add(new Category("Movies", "Movies category"));
        categories.add(new Category("Books", "Books category"));

        users.add(new User("Bango", "mb", "Matej", "Bangievski"));
        users.add(new User("Admin", "admin", "Admin", "Admin"));
    }
}
