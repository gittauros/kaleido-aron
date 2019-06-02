package com.tauros.kaleido.aron.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "kaleido-aron", path = "")
public interface AronService {

    @RequestMapping("/redis/putTestObject")
    String putRedisObject();

    @RequestMapping("/redis/getTestObject")
    Object getRedisObject();
}
