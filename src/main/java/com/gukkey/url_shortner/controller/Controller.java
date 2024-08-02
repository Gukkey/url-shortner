package com.gukkey.url_shortner.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gukkey.url_shortner.service.Service;

@RestController
@RequestMapping(path = "/api/")
public class Controller {

    @Autowired
    Service serviceImpl;
    
    @GetMapping("/stats/{shortCode}")
    public long getAccessCount(@PathVariable String shortCode) {
        return serviceImpl.returnAccessCount(shortCode);
    }


    @GetMapping("/{shortCode}")
    public String getShortURL(@PathVariable String shortCode) {
        return serviceImpl.retrieveURLMappings(shortCode);
    }

    @PostMapping("/shorten")
    public String createShortURL(@RequestBody String url) {
        return serviceImpl.generateShortCode(url);
    }

    @GetMapping("/")
    public String hello() {
        return "hello";
    }
}
