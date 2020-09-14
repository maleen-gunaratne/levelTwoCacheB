package com.cache.levelTwo.contoller;

import com.cache.levelTwo.model.Configuration;
import com.cache.levelTwo.model.Order;
import com.cache.levelTwo.service.cache_service.CacheService;
import com.google.gson.JsonObject;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cache")
public class LevelTwoController {

    @Autowired
    CacheService cacheService;

    @PostMapping("/initCache")
    public String initCache(@RequestBody Configuration configuration) {
        Configuration  c = new Configuration();

        System.out.print("filesystem max size"+configuration.getFileSystemMaxSize());

        c.setFileSystemMaxSize(configuration.getFileSystemMaxSize());
        c.setMemoryMaxSize(configuration.getMemoryMaxSize());
        c.setCacheType(configuration.getCacheType());

        cacheService.init(c);

        return "Cache Successfully Configured!";
    }

    @PostMapping("/add")
    public String addToCache(@RequestBody Order order) {

        System.out.print(order.getName());

        List<Order> list = cacheService.add(order);

        List cachelist = cacheService.getAll();

        String allorders= (String) cachelist.get(0);

        return allorders;
    }


    @GetMapping("/update/{orderNo}")
    public String getOrder(@PathVariable String orderNo){

        Order order = cacheService.update(orderNo);

        List list = cacheService.getAll();

        String allOrders = (String) list.get(0);

        return allOrders;
    }

    @GetMapping("/getall")
    public String getallOrder(){

        List list = cacheService.getAll();

        String allOrders = (String) list.get(0);

        return allOrders;
    }
}
