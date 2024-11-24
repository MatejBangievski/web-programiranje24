package matejbangievski.webaud.service.impl;

import matejbangievski.webaud.model.Product;
import matejbangievski.webaud.model.ShoppingCart;
import matejbangievski.webaud.model.User;
import matejbangievski.webaud.model.enumerations.ShoppingCartStatus;
import matejbangievski.webaud.model.exceptions.ProductAlreadyInShoppingCartException;
import matejbangievski.webaud.model.exceptions.ProductNotFoundException;
import matejbangievski.webaud.model.exceptions.ShoppingCartNotFoundException;
import matejbangievski.webaud.model.exceptions.UserNotFoundException;
import matejbangievski.webaud.resource.InMemoryShoppingCartRepository;
import matejbangievski.webaud.resource.InMemoryUserRepository;
import matejbangievski.webaud.service.ProductService;
import matejbangievski.webaud.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final InMemoryShoppingCartRepository shoppingCartRepository;
    private final InMemoryUserRepository userRepository;
    private final ProductService productService;

    public ShoppingCartServiceImpl(InMemoryShoppingCartRepository shoppingCartRepository,
                                   InMemoryUserRepository userRepository,
                                   ProductService productService) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
        this.productService = productService;
    }

    @Override
    public List<Product> listAllProductsInShoppingCart(Long cartId) {
        if (shoppingCartRepository.findById(cartId).isEmpty())
            throw new ShoppingCartNotFoundException(cartId);
        return shoppingCartRepository.findById(cartId).get().getProducts();
    }

    @Override
    public ShoppingCart getActiveShoppingCart(String username) {
        return shoppingCartRepository.findByUsernameAndStatus(username, ShoppingCartStatus.CREATED)
                .orElseGet( () -> {
                    User user = userRepository.findByUsername(username)
                            .orElseThrow(() -> new UserNotFoundException(username));
                    ShoppingCart shoppingCart = new ShoppingCart(user);
                    return shoppingCartRepository.save(shoppingCart);
                });
    }

    @Override
    public ShoppingCart addProductToShoppingCart(String username, Long productId) {
        ShoppingCart shoppingCart = getActiveShoppingCart(username);
        Product product = productService.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
        if (shoppingCart.getProducts()
                .stream().filter(p -> p.getId().equals(productId))
                .collect(Collectors.toList()).size() > 0)
            throw new ProductAlreadyInShoppingCartException(productId, username);
        shoppingCart.getProducts().add(product);
        return shoppingCartRepository.save(shoppingCart);
    }
}
