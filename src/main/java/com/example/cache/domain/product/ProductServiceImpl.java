package com.example.cache.domain.product;

import com.example.cache.domain.product.model.Product;
import com.example.cache.repository.product.ProductRepository;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
  private final ProductRepository productRepository;

  public ProductServiceImpl(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Cacheable(value = "products", key = "'allProducts'", cacheManager = "redisCacheManager")
  public List<Product> getAllProducts() {
    log.info("Getting all products from the database");
    return productRepository.findAll();
  }

  @Cacheable(value = "products", key = "#id", cacheManager = "redisCacheManager")
  public Product getProductById(Long id) {
    log.info("Getting product with ID {} from the database", id);
    return productRepository.findById(id);
  }

  @CachePut(value = "products", key = "#result.id", cacheManager = "redisCacheManager")
  @CacheEvict(value = "products", key = "allProducts", cacheManager = "caffeineCacheManager")
  public Product saveProduct(Product product) {
    log.info("Saving product to the database");
    return productRepository.save(product);
  }

  @Caching(evict = {
      @CacheEvict(value = "products", key = "#id", cacheManager = "caffeineCacheManager"),
      @CacheEvict(value = "products", key = "allProducts", cacheManager = "caffeineCacheManager")
  })
  public void deleteProduct(Long id) {
    log.info("Deleting product with ID {} from the database", id);
    productRepository.deleteById(id);
  }
}
