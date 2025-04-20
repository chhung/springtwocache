package com.example.cache.web;

import com.example.cache.domain.product.ProductService;
import com.example.cache.domain.product.model.Product;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductCtl {
  private final ProductService productService;

  public ProductCtl(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping
  public ResponseEntity<List<Product>> getAllProducts() {
    return ResponseEntity.ok(productService.getAllProducts());
  }

  @GetMapping("/{id}")
  public ResponseEntity<Product> getProductById(@PathVariable Long id) {
    Product product = productService.getProductById(id);
    if (product == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(product);
  }

  @PostMapping
  public ResponseEntity<Product> createProduct(@RequestBody Product product) {
    return new ResponseEntity<>(productService.saveProduct(product), HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
    Product existingProduct = productService.getProductById(id);
    if (existingProduct == null) {
      return ResponseEntity.notFound().build();
    }
    product.setId(id);
    return ResponseEntity.ok(productService.saveProduct(product));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
    Product existingProduct = productService.getProductById(id);
    if (existingProduct == null) {
      return ResponseEntity.notFound().build();
    }
    productService.deleteProduct(id);
    return ResponseEntity.noContent().build();
  }
}
