package com.cache.levelTwo.service.cache_service;

import com.cache.levelTwo.model.Configuration;
import com.cache.levelTwo.model.Order;

import java.util.List;

public interface CacheService {

    String init(Configuration configuration);

    List<Order> add(Order order);

    Order update(String orderNo);

    List<Order> getAll();


}
