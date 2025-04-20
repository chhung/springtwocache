package com.example.cache.repository.product;

import com.example.cache.domain.product.model.Product;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
  private final Map<Long, Product> productMap = new HashMap<>();
  private long sequence = 1;

  public ProductRepositoryImpl() {
    // 預設添加一些產品數據
    save(new Product(null, "iPhone 13", 799.99, "Apple smartphone"));
    save(new Product(null, "Galaxy S22", 699.99, "Samsung flagship smartphone"));
    save(new Product(null, "MacBook Pro", 1999.99, "Apple laptop"));
    save(new Product(null, "Pixel 6", 599.99, "Google smartphone"));
  }

  @Override
  public List<Product> findAll() {
    // 模擬數據庫查詢延遲
    simulateSlowService();
    return new ArrayList<>(productMap.values());
  }

  @Override
  public Product findById(Long id) {
    // 模擬數據庫查詢延遲
    simulateSlowService();
    return productMap.get(id);
  }

  @Override
  public Product save(Product product) {
    if (product.getId() == null) {
      product.setId(sequence++);
    }
    productMap.put(product.getId(), product);
    return product;
  }

  @Override
  public void deleteById(Long id) {
    productMap.remove(id);
  }

  private void simulateSlowService() {
    try {
      // 模擬 500ms 延遲
      Thread.sleep(500);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }
}
