package com.banquito.core.product.config;

import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CacheEventLogger implements CacheEventListener<Object, Object> {

    private static final Logger logger = LoggerFactory.getLogger(CacheEventLogger.class);

    
    public void onEvent(CacheEvent<? extends Object, ? extends Object> cacheEvent) {
        logger.info("Cache event = {}, Key = {}, Old value = {}, New value = {}",
                cacheEvent.getType(), cacheEvent.getKey(), cacheEvent.getOldValue(), cacheEvent.getNewValue());
    }
}
