package com.cache.levelTwo.service.cache_service.impl;

import com.cache.levelTwo.model.Configuration;
import com.cache.levelTwo.model.Order;
import com.cache.levelTwo.service.cache_service.CacheService;
import com.cache.levelTwo.service.cache_service.CashPolicies.PolicyType;
import com.cache.levelTwo.service.storage.Cache;
import com.cache.levelTwo.service.storage.impl.TwoLevelCache;
import com.google.gson.JsonObject;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CacheServiceImpl implements CacheService {

    private Cache cache;

    @Override
    public String init(Configuration configuration) {

        String errorMessage="";
        try {
            if(configuration.getMemoryMaxSize().isEmpty() |configuration.getMemoryMaxSize() == null){
                errorMessage = "Enter Max  MemorySize";

            }else if(configuration.getCacheType() == null || configuration.getCacheType().isEmpty()){
                errorMessage = "Caching Type was not found";
            }else if(Integer.parseInt(configuration.getMemoryMaxSize()) <= 0){
                errorMessage = "max memory Size should be more than 0";
            }else {

                int memorymaxSize = Integer.parseInt(configuration.getMemoryMaxSize());
                int fileSystemmaxSize = Integer.parseInt(configuration.getFileSystemMaxSize());

                if (configuration.getCacheType().equalsIgnoreCase("LFU")) {

                    cache = new TwoLevelCache<>(memorymaxSize,fileSystemmaxSize);
                } else {

                    cache = new TwoLevelCache<>(memorymaxSize,fileSystemmaxSize,PolicyType.LEAST_RECENTLY_USED);
                }
            }
        } catch (Exception e) {
            errorMessage = "Internal Server Error Occured";
        }

        return errorMessage;
    }

    @Override
    public List<Order> add(Order order) {
        boolean keyExist = false;
        keyExist = cache.isObjectContained(order.getOrderNo());

        System.out.println(order);
        System.out.println(order.getOrderNo());
        System.out.println(order.getName());

        if(!keyExist){
            order.setAccessCount(0);
        }

        cache.putIntoCache(order.getOrderNo(),order);

        List<Order> list = getAll();

        return null;
    }

    @Override
    public Order update(String orderNo) {
        Order or = new Order();
        Order order = (Order) cache.getFromCache(orderNo);
        or.setOrderNo(order.getOrderNo());
        or.setCardType(order.getCardType());
        or.setName(order.getName());
        or.setAccessCount(order.getAccessCount()+1);

        cache.putIntoCache(orderNo,or);

        return order;
    }

    @Override
    public List getAll() {

       List all= cache.getAll();

       return  all;

    }
}
