package com.example.cache.repository.product;

import com.example.cache.domain.product.model.Product;
import java.util.List;

public interface ProductRepository {

  List<Product> findAll();

  Product findById(Long id);

  Product save(Product product);

  void deleteById(Long id);
}
