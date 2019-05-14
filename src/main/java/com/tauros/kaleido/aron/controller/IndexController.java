package com.tauros.kaleido.aron.controller;

import com.tauros.kaleido.aron.repository.RedisRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by zhy on 2018/10/27.
 */
@Controller
@Slf4j
public class IndexController {

    @Resource
    private RedisRepository redisRepository;

    @RequestMapping(value = {"/", "/index", "/home"})
    @ResponseBody
    public String index() {
        return "kaleido-aron hello!";
    }

}
