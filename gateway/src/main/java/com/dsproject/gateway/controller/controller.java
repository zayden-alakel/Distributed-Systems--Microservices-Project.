package com.dsproject.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gateway")
public class controller {

    @GetMapping
    public String getGatewayInfo() {
        return "Gateway Service";
    }
}