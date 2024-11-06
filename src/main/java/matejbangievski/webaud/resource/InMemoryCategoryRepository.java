package matejbangievski.webaud.resource;

import matejbangievski.webaud.bootstrap.DataHolder;
import matejbangievski.webaud.model.Category;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//Repository for handling the in-memory storage of categories
@Repository
public class InMemoryCategoryRepository {
    public Category save(Category category) {
        //If the category already exists, remove it and add the new one
        DataHolder.categories.removeIf(c -> c.getName().equals(category.getName()));
        DataHolder.categories.add(category);

        return category;
    }

    public List<Category> findAll() {
        return DataHolder.categories;
    }

    public Optional<Category> findByName(String name) {
        return DataHolder.categories.stream().filter(c -> c.getName().equals(name)).findFirst();
    }

    public List<Category> search(String text) {
        return DataHolder.categories.stream().filter(c -> c.getName().contains(text) || c.getDescription().contains(text)).collect(Collectors.toList());
    }

    public void delete(String name) {
        DataHolder.categories.removeIf(c -> c.getName().equals(name));
    }
}
