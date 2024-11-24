package matejbangievski.webaud.resource;

import matejbangievski.webaud.bootstrap.DataHolder;
import matejbangievski.webaud.model.Category;
import matejbangievski.webaud.model.Manufacturer;
import matejbangievski.webaud.model.Product;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryProductRepository {
    public List<Product> findAll() {
        return DataHolder.products;
    }

    public Optional<Product> findById (Long productId) {
        return DataHolder.products.stream().filter(p -> p.getId().equals(productId)).findFirst();
    }

    public Optional<Product> findByName (String name) {
        return DataHolder.products.stream().filter(p -> p.getName().equals(name)).findFirst();
    }

    public void deleteById (Long id) {
        DataHolder.products.removeIf(p -> p.getId().equals(id));
    }

    public Optional<Product> save (String name, Double price, Integer quantity, Category category, Manufacturer manufacturer) {
        // Create/Update
        if (category == null || manufacturer == null) {
            throw new IllegalArgumentException();
        }

        Product product = new Product(name, price, quantity, category, manufacturer);
        DataHolder.products.removeIf(p -> p.getName().equals(product.getName()));
        DataHolder.products.add(product);

        return Optional.of(product);
    }
}
