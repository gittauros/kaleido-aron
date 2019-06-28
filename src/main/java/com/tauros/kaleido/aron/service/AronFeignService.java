package com.tauros.kaleido.aron.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zhy
 * @date 2019/6/28
 */
@FeignClient(value = "kaleido-aron", path = "redis")
public interface AronFeignService {

    @RequestMapping("putTestObject")
    String putRedisObject();

    @RequestMapping("getTestObject")
    Object getRedisObject();
}
