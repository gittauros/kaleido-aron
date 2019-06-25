package com.tauros.kaleido.aron.service.impl;

import com.tauros.kaleido.aron.repository.RedisRepository;
import com.tauros.kaleido.aron.service.AronService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author zhy
 * @date 2019/6/21
 */
//@Service(version = "1.0.0")
@Slf4j
public class AronServiceImpl implements AronService {

    @Resource
    private RedisRepository redisRepository;

    @Override
    public String putRedisObject() {
        try {
            redisRepository.putTestObj();
            return "success";
        } catch (IOException e) {
            log.error("putRedisObject fail", e);
            return "fail";
        }
    }

    @Override
    public Object getRedisObject() {
        return redisRepository.getTestObject();
    }
}
