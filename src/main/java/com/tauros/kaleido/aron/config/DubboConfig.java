package com.tauros.kaleido.aron.config;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhy
 * @date 2019/6/21
 */
@Configuration
@EnableDubbo(scanBasePackages = "com.tauros.kaleido.aron")
public class DubboConfig {
}
