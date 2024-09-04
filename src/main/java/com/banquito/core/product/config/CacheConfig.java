package com.banquito.core.product.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.cache.Caching;
import javax.cache.CacheManager;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public JCacheCacheManager cacheManager() {
        CacheManager cacheManager = Caching.getCachingProvider().getCacheManager();
        return new JCacheCacheManager(cacheManager);
    }
}
