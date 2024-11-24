package matejbangievski.webaud.service;

import matejbangievski.webaud.model.Product;
import matejbangievski.webaud.model.ShoppingCart;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ShoppingCartService {

    List<Product> listAllProductsInShoppingCart(Long cartId);

    ShoppingCart getActiveShoppingCart(String username);

    ShoppingCart addProductToShoppingCart(String username, Long productId);
}
