package com.tauros.kaleido.aron.controller;

import com.tauros.kaleido.aron.service.AronService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/serviceTest")
@Slf4j
public class ServiceTestController {

//    @Reference(version = "1.0.0")
//    private AronService aronService;
//
//    @RequestMapping("/testPut")
//    public String testPut() {
//        log.info("testPut visit this");
//        return aronService.putRedisObject();
//    }
//
//    @RequestMapping("/testGet")
//    public Object testGet() {
//        log.info("testGet visit this");
//        return aronService.getRedisObject();
//    }

}
