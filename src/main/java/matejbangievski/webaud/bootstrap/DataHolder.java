package matejbangievski.webaud.bootstrap;

import jakarta.annotation.PostConstruct;
import matejbangievski.webaud.model.Category;
import matejbangievski.webaud.model.Manufacturer;
import matejbangievski.webaud.model.Product;
import matejbangievski.webaud.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//in-memory data holder
@Component
public class DataHolder {
    public static List<Category> categories = null; //static ne zaboravaj
    public static List<Manufacturer> manufacturers = null; //static ne zaboravaj
    public static List<Product> products = null; //static ne zaboravaj
    public static List<User> users = null;

    //On application startup, initialize the categories list
    //On each startup, the list will be initialized with the same values and the previous values will be lost
    @PostConstruct
    public void init() {
        categories = new ArrayList<>();
        categories.add(new Category("Sports", "Sports category"));
        categories.add(new Category("Food", "Food category"));
        categories.add(new Category("Books", "Books category"));

        users = new ArrayList<>();
        users.add(new User("Bango", "mb", "Matej", "Bangievski"));
        users.add(new User("Admin", "admin", "Admin", "Admin"));

        manufacturers = new ArrayList<>();
        manufacturers.add(new Manufacturer("Nike", "USA"));
        manufacturers.add(new Manufacturer("Coca Cola", "USA"));
        manufacturers.add(new Manufacturer("Literatura", "MK"));

        products = new ArrayList<>();
        products.add(new Product("Nike Air Max", 100.0, 10, categories.get(0), manufacturers.get(0)));
        products.add(new Product("Coca Cola 2L", 2.0, 100, categories.get(1), manufacturers.get(1)));
        products.add(new Product("Java Programming", 100.0, 10, categories.get(2), manufacturers.get(2)));
    }
}
