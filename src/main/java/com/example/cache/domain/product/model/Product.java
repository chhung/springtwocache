package com.example.cache.domain.product.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {
  private static final long serialVersionUID = 1L;

  private Long id;
  private String name;
  private double price;
  private String description;
}
