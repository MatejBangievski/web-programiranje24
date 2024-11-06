package matejbangievski.webaud.service.impl;

import matejbangievski.webaud.model.Category;
import org.springframework.stereotype.Service;
import matejbangievski.webaud.resource.InMemoryCategoryRepository;
import matejbangievski.webaud.service.CategoryService;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final InMemoryCategoryRepository categoryRepository;

    public CategoryServiceImpl(InMemoryCategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> listCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category create(String name, String description) {
        if (name == null || description == null || name.isEmpty() || description.isEmpty()) {
            throw new IllegalArgumentException();
        }

        Category category = new Category(name, description);
        return categoryRepository.save(category);
    }

    @Override
    public Category update(String name, String description) {
        if (name == null || description == null || name.isEmpty() || description.isEmpty()) {
            throw new IllegalArgumentException();
        }

        Category category = new Category(name, description);
        return categoryRepository.save(category);
    }

    @Override
    public void remove(String name) {
        categoryRepository.delete(name);
    }

    @Override
    public List<Category> searchCategories(String text) {
        return categoryRepository.search(text);
    }
}
