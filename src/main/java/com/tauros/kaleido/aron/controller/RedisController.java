package com.tauros.kaleido.aron.controller;

import com.tauros.kaleido.aron.repository.RedisRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author zhy
 * @date 2019/5/14
 */
@Controller
@RequestMapping("redis")
@Slf4j
public class RedisController {

    @Resource
    private RedisRepository redisRepository;

    @RequestMapping("/putTestObject")
    @ResponseBody
    public String putTestObject() {
        try {
            redisRepository.putTestObj();
            return "success\n";
        } catch (Exception e) {
            log.error("putTestObject fail", e);
            return "fail\n";
        }
    }

    @RequestMapping("/getTestObject")
    @ResponseBody
    public Object getTestObject() {
        try {
            return redisRepository.getTestObject();
        } catch (Exception e) {
            log.error("getTestObject fail", e);
            return "fail\n";
        }
    }
}
