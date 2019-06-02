package com.tauros.kaleido.aron.controller;

import com.tauros.kaleido.aron.service.AronService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/serviceTest")
@Slf4j
public class ServiceTestController {

    @Resource
    private AronService aronService;

    @RequestMapping("/testPut")
    public String testPut() {
        return aronService.putRedisObject();
    }

    @RequestMapping("/testGet")
    public Object testGet() {
        return aronService.getRedisObject();
    }

}
