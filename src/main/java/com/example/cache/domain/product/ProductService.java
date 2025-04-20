package com.example.cache.domain.product;

import com.example.cache.domain.product.model.Product;
import java.util.List;

public interface ProductService {
  List<Product> getAllProducts();
  Product getProductById(Long id);
  Product saveProduct(Product product);
  void deleteProduct(Long id);
}
