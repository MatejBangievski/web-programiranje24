package matejbangievski.webaud.service.impl;

import matejbangievski.webaud.model.Category;
import matejbangievski.webaud.model.Manufacturer;
import matejbangievski.webaud.model.Product;
import matejbangievski.webaud.model.exceptions.CategoryNotFoundException;
import matejbangievski.webaud.model.exceptions.ManufacturerNotFoundException;
import matejbangievski.webaud.resource.InMemoryCategoryRepository;
import matejbangievski.webaud.resource.InMemoryManufacturerRepository;
import matejbangievski.webaud.resource.InMemoryProductRepository;
import matejbangievski.webaud.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final InMemoryProductRepository productRepository;
    private final InMemoryManufacturerRepository manufacturerRepository;
    private final InMemoryCategoryRepository categoryRepository;

    public ProductServiceImpl(InMemoryProductRepository productRepository,
                              InMemoryManufacturerRepository manufacturerRepository,
                              InMemoryCategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.manufacturerRepository = manufacturerRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Optional<Product> findByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public Optional<Product> save(String name, Double price, Integer quantity, Long categoryId, Long manufacturerId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
        Manufacturer manufacturer = manufacturerRepository.findById(manufacturerId)
                .orElseThrow(() -> new ManufacturerNotFoundException(manufacturerId));
        return  productRepository.save(name, price, quantity, category, manufacturer);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
