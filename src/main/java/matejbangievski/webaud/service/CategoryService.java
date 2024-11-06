package matejbangievski.webaud.service;

import matejbangievski.webaud.model.Category;

import java.util.List;

public interface CategoryService {
    //list
    List<Category> listCategories();
    //create
    Category create(String name, String description);
    //update
    Category update(String name, String description);
    //delete
    void remove(String name);
    //search
    List<Category> searchCategories(String text);
}
