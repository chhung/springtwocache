package com.example.cache.web;

import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cache")
public class CacheCtl {
  private final CacheManager cacheManager;

  public CacheCtl(CacheManager cacheManager) {
    this.cacheManager = cacheManager;
  }

  @DeleteMapping("/clear/{cacheName}")
  public void clearCache(@PathVariable String cacheName) {
    cacheManager.getCache(cacheName).clear();
  }
}
