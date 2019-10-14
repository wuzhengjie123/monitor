package com.xy_asia.monitor.controller;

import com.xy_asia.monitor.service.OrderGaugeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class TestController{

    @Autowired
    private OrderGaugeService orderGaugeService;

    @GetMapping("/test")
    private int test(){
        orderGaugeService.setGauge();
        return 1;
    }

}
