package com.tauros.kaleido.aron.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zhy on 2018/10/27.
 */
@Controller
@Slf4j
public class IndexController {

    @RequestMapping(value = {"/", "/index", "/home"})
    @ResponseBody
    public String index() {
        return "kaleido-aron";
    }
}
