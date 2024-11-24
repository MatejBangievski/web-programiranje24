package matejbangievski.webaud.service;

import matejbangievski.webaud.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    //list
    List<Category> listCategories();

    //create
    Optional<Category> create(String name, String description);

    //update
    Optional<Category> update(String name, String description);

    //delete
    void delete(String name);
    void deleteById(Long id);

    //search
    List<Category> searchCategories(String text);
    Optional<Category> findById(Long id);
}
